package ir.hadiagdamapps.behzaddb.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

@Composable
fun TextInput(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
) {

    TextField(text, onTextChange, placeholder = {
        Text(placeholder)
    }, modifier = modifier)
}


@Preview
@Composable
private fun TextInputPreview() {
    ApplicationTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            var text by remember { mutableStateOf("") }
            TextInput(text, { text = it }, placeholder = "Hint")
        }
    }
}
