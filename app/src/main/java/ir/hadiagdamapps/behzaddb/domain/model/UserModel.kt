package ir.hadiagdamapps.behzaddb.domain.model

import java.time.LocalDateTime

data class UserModel(
    val userId: Int,
    val username: String,
    val password: String,
    val name: String,
    val registerDate: LocalDateTime,
    val accessList: List<Int> = listOf()
)
