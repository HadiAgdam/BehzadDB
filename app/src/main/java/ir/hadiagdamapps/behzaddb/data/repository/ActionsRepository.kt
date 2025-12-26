package ir.hadiagdamapps.behzaddb.data.repository

import android.content.Context
import ir.hadiagdamapps.behzaddb.data.local.ApplicationDatabaseHelper
import ir.hadiagdamapps.behzaddb.domain.model.ActionModel

class ActionsRepository(private val context: Context) {

    private val db = ApplicationDatabaseHelper(context)

    fun getActionById(actionId: Int): ActionModel {

        return db.getActions().first { it.actionId == actionId }

    }
}