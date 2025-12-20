package ir.hadiagdamapps.behzaddb.domain

import java.time.LocalDateTime

data class SystemModel(
    val systemId: Int,
    val name: String,
    val status: String,
    val registerDate: LocalDateTime
)