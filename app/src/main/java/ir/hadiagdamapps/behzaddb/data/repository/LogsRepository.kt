package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.domain.model.LogModel
import java.time.LocalDateTime

class LogsRepository(private val context: Context) {

    fun getByUser(userId: Int): List<LogModel> {
        // TODO
        return (0..100).map { LogModel(
            it,
            0,
            0,
            0,
            LocalDateTime.now(),
            "info"
        ) }
    }

}