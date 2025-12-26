package ir.hadiagdamapps.behzaddb

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hadiagdamapps.behzaddb.data.repository.ActionsRepository
import ir.hadiagdamapps.behzaddb.data.repository.LogsRepository
import ir.hadiagdamapps.behzaddb.data.repository.SystemsRepository
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.domain.model.ActionModelEnum
import ir.hadiagdamapps.behzaddb.domain.model.LogModel
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import ir.hadiagdamapps.behzaddb.domain.model.UserModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.MenuItemButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput
import ir.hadiagdamapps.behzaddb.ui.model.LogViewModel
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme
import java.time.LocalDateTime

class UserProfileActivity : BaseActivity() {

    private val userRepository = UserRepository(this)
    private val logsRepository = LogsRepository(this)
    private val actionsRepository = ActionsRepository(this)
    private val systemsRepository = SystemsRepository(this)
    private lateinit var user: UserModel
    private lateinit var system: SystemModel
    private val actionNames = HashMap<Int, String>()
    private val systemNames = HashMap<Int, String>()

    @Composable
    fun MainContent(name: String, username: String) {
        val logs = remember { mutableStateListOf<LogModel>() }
        var savedName by remember { mutableStateOf(name) }
        var nameEditable by remember { mutableStateOf(name) }
        var saveButtonEnabled by remember { mutableStateOf(false) }

        logs.addAll(logsRepository.getByUser(user.userId))

        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(username, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Spacer(Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                TextInput(
                    nameEditable, onTextChange = {
                        nameEditable = it
                        Log.e("nameEditable", nameEditable)
                        Log.e("savedName", savedName)
                        saveButtonEnabled = nameEditable != savedName
                    }, placeholder = "Name",
                    modifier = Modifier.weight(1f)
                )
                Spacer(
                    Modifier
                        .width(8.dp)
                        .height(24.dp)
                )
                Button(
                    {
                        userRepository.updateName(user.userId, nameEditable)
                        logsRepository.saveLog(
                            LogModel(
                                logId = -1,
                                systemId = system.systemId,
                                actionId = ActionModelEnum.UPDATE_NAME.actionId,
                                userId = user.userId,
                                info = "Name updated from UserProfileActivity",
                            )
                        )
                        savedName = nameEditable
                        saveButtonEnabled = false
                        Toast.makeText(this@UserProfileActivity, "saved!", Toast.LENGTH_SHORT)
                            .show()
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = ApplicationColor.editButtonColor),
                    modifier = Modifier.height(48.dp),
                    enabled = saveButtonEnabled
                ) {
                    Text("Save Name")
                }
            }
            Spacer(Modifier.height(24.dp))
            MenuItemButton("Change Password", onClick = {
                Intent(this@UserProfileActivity, ChangePasswordActivity::class.java).apply {
                    this.putExtra("userId", user.userId)
                    this.putExtra("systemId", system.systemId)
                    startActivity(this)
                }
            })
            Spacer(Modifier.height(24.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Logs", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Button({
                    logs.clear()
                    logs.addAll(logsRepository.getByUser(user.userId))
                }) { Text("Reload") }
            }
            Spacer(Modifier.height(24.dp))
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(logs.asReversed()) { log ->
                    LogViewModel(
                        log.actionId.let { actionId ->
                            if (actionNames.keys.contains(actionId))
                                actionNames[actionId]!!
                            else
                                actionsRepository.getActionById(actionId).name
                                    .also { name -> actionNames[actionId] = name }
                        },
                        systemId = log.systemId.let { systemId ->
                            if (systemNames.keys.contains(systemId))
                                systemNames[systemId]!!
                            else
                                systemsRepository.getBySystemId(systemId).name
                                    .also { name -> systemNames[systemId] = name }
                        },
                        username = username,
                        time = log.date
                    )
                }
            }
        }

    }


    @Composable
    override fun Main() {
        intent.extras?.apply {
            user = userRepository.getUserById(getInt("userId"))!!
            system = systemsRepository.getBySystemId(getInt("systemId"))
        }
        logsRepository.saveLog(
            LogModel(
                logId = -1,
                actionId = ActionModelEnum.LOGIN.actionId,
                userId = user.userId,
                systemId = system.systemId,
                date = LocalDateTime.now(),
                info = "Login saved from UserProfile Activity"
            )
        )

        MainContent(user.name, user.username)
    }


    @Preview
    @Composable
    private fun UserProfilePreview() {
        ApplicationTheme {
            MainContent(
                name = "Hadi",
                username = "Numixgamer"
            )
        }
    }
}