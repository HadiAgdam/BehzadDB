package ir.hadiagdamapps.behzaddb.domain.model

import java.time.LocalDateTime

data class LogModel(
    val logId: Int,
    val actionId: Int,
    val userId: Int,
    val systemId: Int,
    val date: LocalDateTime,
    val info: String
)
