package ir.hadiagdamapps.behzaddb

import android.content.Intent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import ir.hadiagdamapps.behzaddb.data.repository.SystemsRepository
import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.model.SystemViewModel
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

class SystemsActivity : BaseActivity() {

    val repository = SystemsRepository(this)
    private var userId = -1

    private fun onItemClick(systemId: Int) {
        Intent(this, UserProfileActivity::class.java).apply {
            putExtra("userId", userId)
            putExtra("systemId", systemId)
            startActivity(this)
        }
    }

    @Composable
    fun Content(systems: List<SystemModel>) {
        val content = remember { mutableStateListOf<SystemModel>() }
        content.addAll(systems)
        LazyColumn {
            items(content) {
                SystemViewModel(it) {
                    onItemClick(it.systemId)
                }
            }
        }
    }

    @Composable
    override fun Main() {
        userId = intent?.extras?.getInt("userId")!!
        Content(repository.getByUserId(userId))
    }

    @Preview
    @Composable
    private fun SystemScreenPreview() {
        ApplicationTheme {
            Content(listOf())
        }
    }
}