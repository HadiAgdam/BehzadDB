package ir.hadiagdamapps.behzaddb.ui.model

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hadiagdamapps.behzaddb.domain.model.LogModel
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun LogViewModel(
    action: String,
    systemId: String,
    username: String,
    time: LocalDateTime,
    onClick: () -> Unit = {}
) {
    val time = time.let { "${it.year}/${it.monthValue}/${it.dayOfMonth} ${it.hour}:${it.minute}" }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(1.dp, color = Color.Gray)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(action, fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(time)
        }
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(systemId)
            Text(username)
        }
    }
}


@Preview
@Composable
private fun LogViewModelPreview() {
    ApplicationTheme {
        Column(Modifier.fillMaxSize()) {
            LogViewModel(
                action = "ACTION",
                systemId = "SYSTEM_ID",
                username = "USERNAME",
                time = LocalDateTime.now()
            )
        }
    }
}