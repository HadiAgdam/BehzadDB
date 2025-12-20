package ir.hadiagdamapps.behzaddb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import ir.hadiagdamapps.behzaddb.ui.theme.ApplicationTheme

abstract class BaseActivity : ComponentActivity() {

    @Composable
    protected abstract fun Main()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                    Main()
            }
        }
    }
}