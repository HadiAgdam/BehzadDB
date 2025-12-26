package ir.hadiagdamapps.behzaddb.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.annotation.RawRes
import ir.hadiagdamapps.behzaddb.R
import ir.hadiagdamapps.behzaddb.domain.model.ActionModel
import ir.hadiagdamapps.behzaddb.domain.model.LogModel
import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import ir.hadiagdamapps.behzaddb.domain.model.UserModel
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ApplicationDatabaseHelper(private val context: Context) :
    SQLiteOpenHelper(context, "ApplicationDatabase", null, 1) {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//
//    private fun readText(@RawRes raw: Int): String =
//        InputStreamReader(context.resources?.openRawResource(raw)!!).readText()

    private fun execSqlFile(db: SQLiteDatabase, resId: Int) {
        val sql = InputStreamReader(context.resources.openRawResource(resId)).readText()
        sql.split(";")
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .forEach { db.execSQL(it) }
    }



    override fun onCreate(db: SQLiteDatabase?) {
        db?.apply {
            execSqlFile(db, R.raw.create_table_queries)
            execSqlFile(db, R.raw.sample_data)
        }
        Log.e("ApplicationDatabaseHelper", "onCreate")
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val query =
            InputStreamReader(context.resources?.openRawResource(R.raw.delete_table_queries)!!).readText()
        db?.execSQL(query)
        onCreate(db)
    }

    // Actions =====================================================================================
    fun getActions(): List<ActionModel> {
        val result = ArrayList<ActionModel>()

        val query = readableDatabase.query(
            "Actions",
            arrayOf("ActionId", "Name", "Description", "Category"),
            "",
            null,
            null,
            null,
            null
        )

        if (query.moveToFirst())
            do ActionModel(
                actionId = query.getInt(0),
                name = query.getString(1),
                description = query.getString(2),
                category = query.getString(3)
            ).also { result.add(it) }
            while (query.moveToNext())

        query.close()

        return result
    }

    // =============================================================================================
    // Logs ========================================================================================
    fun getLogs(): List<LogModel> {
        val result = ArrayList<LogModel>()

        val query = readableDatabase.query(
            "Logs",
            arrayOf("LogId", "ActionId", "UserId", "SystemId", "Date", "Info"),
            null,
            null,
            null,
            null,
            null
        )

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

        if (query.moveToFirst()) {
            do {
                val log = LogModel(
                    logId = query.getInt(0),
                    actionId = query.getInt(1),
                    userId = query.getInt(2),
                    systemId = query.getInt(3),
                    date = LocalDateTime.parse(query.getString(4), formatter),
                    info = query.getString(5)
                )
                result.add(log)
            } while (query.moveToNext())
        }

        query.close()
        return result
    }

    fun insertLog(log: LogModel): Long {
        val values = ContentValues().apply {
            put("ActionId", log.actionId)
            put("UserId", log.userId)
            put("SystemId", log.systemId)
            put("Date", log.date.format(formatter))
            put("Info", log.info)
        }

        return writableDatabase.insert("Logs", null, values)
    }

    // =============================================================================================
    // Users =======================================================================================
    fun getUsers(): List<UserModel> {
        val result = ArrayList<UserModel>()

        val query = readableDatabase.query(
            "Users",
            arrayOf("UserId", "Username", "Password", "Name", "RegisterDate", "AccessList"),
            null,
            null,
            null,
            null,
            null
        )

        if (query.moveToFirst())
            do UserModel(
                userId = query.getInt(0),
                username = query.getString(1),
                password = query.getString(2),
                name = query.getString(3),
                registerDate = LocalDateTime.parse(query.getString(4), formatter),
                accessList = query.getString(5)
                    ?.takeIf { it.isNotBlank() }
                    ?.split(",")
                    ?.map { it.toInt() }
                    ?: emptyList(),
            ).also { result.add(it) }
            while (query.moveToNext())

        query.close()

        return result
    }

    fun updateUserName(userId: Int, newName: String): Int {
        val values = ContentValues().apply {
            put("Name", newName)
        }

        return writableDatabase.update(
            "Users",
            values,
            "UserId = ?",
            arrayOf(userId.toString())
        )
    }

    fun updateUserPassword(userId: Int, password: String): Int {
        val values = ContentValues().apply {
            put("Password", password)
        }

        return writableDatabase.update(
            "Users",
            values,
            "UserId = ?",
            arrayOf(userId.toString())
        )
    }

    // =============================================================================================
    // Systems =====================================================================================
    fun getSystems(): List<SystemModel> {
        val result = ArrayList<SystemModel>()

        val query = readableDatabase.query(
            "Systems",
            arrayOf("SystemId", "Name", "Status", "RegisterDate"),
            null,
            null,
            null,
            null,
            null
        )

        if (query.moveToFirst())
            do SystemModel(
                systemId = query.getInt(0),
                name = query.getString(1),
                status = query.getString(2),
                registerDate = LocalDateTime.parse(query.getString(3), formatter),
            ).also { result.add(it) }
            while (query.moveToNext())

        query.close()

        return result
    }
    // =============================================================================================


}