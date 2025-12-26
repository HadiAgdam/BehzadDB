package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.data.local.ApplicationDatabaseHelper
import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import java.time.LocalDateTime

class SystemsRepository(private val context: Context) {

    private val db = ApplicationDatabaseHelper(context)

    fun getByUserId(userId: Int): List<SystemModel> {
        val user = db.getUsers().first { it.userId == userId }

        return db.getSystems().filter { user.accessList.contains(it.systemId) }
    }

    fun getBySystemId(systemId: Int): SystemModel {
        return db.getSystems().first { it.systemId == systemId}
    }


}