package ir.hadiagdamapps.behzaddb.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit
) {
    val screen: @Composable () -> Unit = {
        Box(Modifier.fillMaxSize().background(ApplicationColor.background)) {
            content()
        }
    }
    MaterialTheme(
        typography = Typography,
        content = screen
    )
}