package ir.hadiagdamapps.behzaddb.ui.model

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.hadiagdamapps.behzaddb.domain.SystemModel
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationColor
import java.time.LocalDateTime
import java.util.Date

@Composable
fun SystemViewModel(model: SystemModel) {
    Column(Modifier.fillMaxWidth().padding(24.dp)) {
        Text("Name", fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}


@SuppressLint("NewApi")
@Preview
@Composable
private fun SystemModelPreview() {
    Column(Modifier
        .fillMaxSize()
        .background(ApplicationColor.background)) {
        SystemViewModel(
            SystemModel(
                0,
                "System Name",
                "Online",
                LocalDateTime.now()
            )
        )
    }
}