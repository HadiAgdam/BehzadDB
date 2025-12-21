package ir.hadiagdamapps.behzaddb


import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ir.hadiagdamapps.behzaddb.data.repository.UserRepository
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.SessionModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity

class MainActivity : BaseActivity() {

    private val userRepository = UserRepository(this)
    private lateinit var session: SessionModel

    @Composable
    fun MainContent() {

    }

    @Composable
    override fun Main() {


        session = SessionModel(
            username = intent.extras?.getString("username")!!,
            password = intent.extras?.getString("password")!!,
            systemId = intent.extras?.getInt("systemId")!!
        )



    }


    @Preview
    @Composable
    private fun MainPreview() {

    }
}
