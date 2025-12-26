package ir.hadiagdamapps.behzaddb


import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.SessionModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.MenuItemButton
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

class MainActivity : BaseActivity() {

    private lateinit var session: SessionModel

    @Composable
    fun MainContent() {
        Column(Modifier.fillMaxWidth()) {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton({ finish() }) { Icon(Icons.Default.ExitToApp, null) }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                MenuItemButton("Profile", {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            UserProfileActivity::class.java
                        )
                    )
                })
                MenuItemButton("Work", {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            UserProfileActivity::class.java
                        )
                    )
                })

            }
        }
    }

    @Composable
    override fun Main() {
        session = SessionModel(
            username = intent.extras?.getString("username")!!,
            password = intent.extras?.getString("password")!!,
            systemId = intent.extras?.getInt("systemId")!!
        )

        MainContent()
    }


    @Preview
    @Composable
    private fun MainPreview() {
        ApplicationTheme {
            MainContent()
        }
    }
}
