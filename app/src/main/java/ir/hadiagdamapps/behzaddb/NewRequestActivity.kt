package ir.hadiagdamapps.behzaddb

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hadiagdamapps.behzaddb.ui.BaseActivity
import ir.hadiagdamapps.behzaddb.ui.component.MenuItemButton
import ir.hadiagdamapps.behzaddb.ui.component.TextInput

class NewRequestActivity : BaseActivity() {
    @Composable
    fun MainContent() {

        var title by remember { mutableStateOf("") }

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(24.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Report", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
            Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

            }
        }
    }

    @Composable
    override fun Main() {
        MainContent()
    }

    @Preview
    @Composable
    private fun MainPreview() {
        
    }
}