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

    val repository = SystemsRepository()


    private fun onItemClick(systemId: Int) {
        Intent(this, LoginActivity::class.java).apply {
            putExtra("systemId", systemId)
            startActivity(intent)
        }
    }

    @Composable
    fun Content() {
        val content = remember { mutableStateListOf<SystemModel>() }
        content.addAll(repository.getAll())
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
        // TODO check if user is signed in
        // TODO if user is signed in, redirect to main
        Content()
    }

    @Preview
    @Composable
    private fun SystemScreenPreview() {
        ApplicationTheme {
            Content()
        }
    }
}