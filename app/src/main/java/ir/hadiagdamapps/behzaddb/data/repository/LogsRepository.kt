package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.data.local.ApplicationDatabaseHelper
import ir.hadiagdamapps.behzaddb.domain.model.LogModel
import java.time.LocalDateTime

class LogsRepository(private val context: Context) {

    private val db = ApplicationDatabaseHelper(context)

    fun getByUser(userId: Int): List<LogModel> {
        return db.getLogs().filter { it.userId == userId }
    }

    fun saveLog(log: LogModel) {
        db.insertLog(log)
    }
}