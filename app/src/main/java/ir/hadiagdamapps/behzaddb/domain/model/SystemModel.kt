package ir.hadiagdamapps.behzaddb.domain.model

import java.time.LocalDateTime

data class SystemModel(
    val systemId: Int,
    val name: String,
    val status: String,
    val registerDate: LocalDateTime
)