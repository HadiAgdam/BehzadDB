package ir.hadiagdamapps.behzaddb.ui.model

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import ir.hadiagdamapps.behzaddb.domain.model.SystemModel
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor
import java.time.LocalDateTime

@Composable
fun SystemViewModel(model: SystemModel, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal=16.dp, vertical = 4.dp)
            .border(1.dp, color = Color.Gray)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Text(model.name, fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(model.systemId.toString())
            Text(model.status)
        }
    }
}


@SuppressLint("NewApi")
@Preview
@Composable
private fun SystemModelPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(ApplicationColor.background)
    ) {
        SystemViewModel(
            SystemModel(
                0,
                "System Name",
                "Online",
                LocalDateTime.now()
            )
        ) {

        }
    }
}