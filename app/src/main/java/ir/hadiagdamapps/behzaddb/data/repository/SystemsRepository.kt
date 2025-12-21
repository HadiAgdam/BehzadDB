package ir.hadiagdamapps.behzaddb.data.repository

import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import java.time.LocalDateTime

class SystemsRepository {

    fun getAll(): List<SystemModel> {
        // TODO("Get from DB")
        return (0 until 10).map { i ->
            SystemModel(
                systemId = i,
                name = "Item ${i + 1}",
                status = if (i % 2 == 0) "Online" else "Offline",
                registerDate = LocalDateTime.now()
            )
        }
    }


}