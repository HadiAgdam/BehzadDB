package ir.hadiagdamapps.behzaddb

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.PrimaryButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor

class LoginActivity : BaseActivity() {

    private val userRepository = UserRepository(this)
    private var systemId = 0

    @Composable
    override fun Main() {
        intent.extras?.getInt("systemId")?.let { systemId = it } ?: {
            startActivity(Intent(this, SystemsActivity::class.java))
            finish()
        }

        MainContent()
    }

    // if success return null else return error
    private fun login(username: String, password: String): String? {
        return if (userRepository.userExist(LoginModel(username, password))) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                putExtra("username", username)
                putExtra("password", password)
                putExtra("systemId", systemId)
            })
            finish()
            null
        } else "user not found"
        // TODO add more conditions like access denied
    }

    @Composable
    fun MainContent() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .background(ApplicationColor.background)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextInput(
                username,
                { username = it; errorText = "" },
                "Username",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))
            TextInput(
                password,
                { password = it; errorText = "" },
                "Password",
                modifier = Modifier.fillMaxWidth()
            )
            Text(errorText, color = Color.Red)
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                PrimaryButton("Login", {
                    login(username, password)?.let {
                        errorText = it
                    }
                })
            }
        }
    }


    @Preview
    @Composable
    private fun LoginActivityPreview() {
        MainContent()
    }
}
