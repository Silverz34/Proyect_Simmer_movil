package com.alixmontesinos.app_simmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alixmontesinos.app_simmer.ui.navigation.AppPrueba
import com.alixmontesinos.app_simmer.ui.navigation.MainScreen
import com.alixmontesinos.app_simmer.ui.theme.App_simmerTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_simmerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainScreen()
                    //CrearReceta(onBackClick = {})
                    AppPrueba()

                }

            }
        }
    }
}
