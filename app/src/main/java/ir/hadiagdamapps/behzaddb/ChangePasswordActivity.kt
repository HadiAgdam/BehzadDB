package ir.hadiagdamapps.behzaddb


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.MenuItemButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

class ChangePasswordActivity : BaseActivity() {

    private val repository = UserRepository(this)
    private lateinit var username: String

    @Composable
    fun MainContent() {
        var passwordText by remember { mutableStateOf("") }
        var passwordRepeatText by remember { mutableStateOf("") }

        Column(Modifier.fillMaxSize().padding(horizontal = 24.dp), verticalArrangement = Arrangement.Center) {
            TextInput(passwordText, { passwordText = it}, "Password", Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            TextInput(passwordRepeatText, {passwordRepeatText = it}, "Password Repeat", Modifier.fillMaxWidth())
            Spacer(Modifier.height(48.dp))
            MenuItemButton("Updated Password", {
                if (passwordText != passwordRepeatText) {
                    Toast.makeText(this@ChangePasswordActivity, "Password repeat does not match", Toast.LENGTH_SHORT).show()
                    passwordRepeatText = ""
                }
                else {
                    repository.updatePassword(username, passwordText)
                    Toast.makeText(this@ChangePasswordActivity, "Password updated", Toast.LENGTH_SHORT).show()
                    finish()
                }
            })
        }
    }

    @Composable
    override fun Main() {
        intent.extras?.apply {
            username = getString("username")!!
        }
        MainContent()
    }


    @Preview
    @Composable
    fun ChangePasswordScreenPreview() {
        ApplicationTheme {
            MainContent()
        }
    }
}