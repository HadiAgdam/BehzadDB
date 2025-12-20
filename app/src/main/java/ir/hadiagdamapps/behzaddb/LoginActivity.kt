package ir.hadiagdamapps.behzaddb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.PrimaryButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor

class LoginActivity : BaseActivity() {

    @Composable
    override fun Main() {
        MainContent()
    }
    @Composable
    fun MainContent() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .background(ApplicationColor.background)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextInput(username, { username = it }, "Username", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(24.dp))
            TextInput(password, { password = it }, "Password", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(24.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) { PrimaryButton("Login", {}) }
        }
    }





    @Preview
    @Composable
    private fun LoginActivityPreview() {
        MainContent()
    }
}
