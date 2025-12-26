package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.data.local.ApplicationDatabaseHelper
import ir.hadiagdamapps.behzaddb.domain.model.LoginModel
import ir.hadiagdamapps.behzaddb.domain.model.UserModel

class UserRepository(private val context: Context) {

    private val db = ApplicationDatabaseHelper(context)

    fun getUserByLogin(login: LoginModel): UserModel? {
        return db.getUsers().firstOrNull {it.username == login.username && it.password == login.password}
    }
    fun getUserById(userId: Int): UserModel? {
        return db.getUsers().first { it.userId == userId }
    }

    fun updateName(userId: Int, newName: String) {
        db.updateUserName(userId, newName)
    }


    fun updatePassword(userId: Int, password: String) {
        db.updateUserPassword(userId, password)
    }

}