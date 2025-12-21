package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.UserModel
import java.time.LocalDateTime

class UserRepository(private val context: Context) {


    fun userExist(login: LoginModel): Boolean {
        // TODO("fetch from db")
        // temp
        return login.username == "test" && login.password == "Test"
    }

    fun getUserByLogin(login: LoginModel): UserModel? {
        // TODO("fetch from db")
        // temp
        return UserModel(
            userId = 0,
            username = login.username,
            password=login.password,
            name = "Test Name",
            roleId = 0,
            registerDate = LocalDateTime.now()

        )
    }

}