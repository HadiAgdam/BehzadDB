package ir.hadiagdamapps.behzaddb.domain.model

import java.time.LocalDateTime

data class UserModel(
    val userId: Int,
    val username: String,
    val password: String,
    val name: String,
    val roleId: Int,
    val registerDate: LocalDateTime
)


//UserId (ID)
//Username (TEXT, UNIQUE)
//PasswordHash (TEXT, NOT NULL)
//Name (TEXT)
//RoleId (FK)
//RegisterDate (DATETIME)
