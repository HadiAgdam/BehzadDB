package ir.hadiagdamapps.behzaddb

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.UserModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.MenuItemButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

class UserProfileActivity : BaseActivity() {

    private val userRepository = UserRepository(this)
    private lateinit var user: UserModel

    @Composable
    fun MainContent(name: String, username: String) {
        var savedName by remember { mutableStateOf(name) }
        var nameEditable by remember { mutableStateOf(name) }
        var saveButtonEnabled by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(24.dp))
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
                Spacer(Modifier
                    .width(8.dp)
                    .height(24.dp))
                Button(
                    {
                        userRepository.updateName(user.username, nameEditable)
                        savedName = nameEditable
                        saveButtonEnabled = false
                        Toast.makeText(this@UserProfileActivity, "saved!", Toast.LENGTH_SHORT).show()
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = ApplicationColor.editButtonColor),
                    modifier = Modifier.height(48.dp),
                    enabled = saveButtonEnabled
                ) {
                    Text("Save")
                }
            }
            Spacer(Modifier.height(24.dp))
            MenuItemButton("Change Password", onClick = {

            })
        }

    }


    @Composable
    override fun Main() {
        user = userRepository.getUserByLogin(
            LoginModel(
                username = intent.extras?.getString("username")!!,
                password = intent.extras?.getString("password")!!
            )
        )!!

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