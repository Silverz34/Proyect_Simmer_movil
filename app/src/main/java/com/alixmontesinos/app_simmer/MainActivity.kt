package com.alixmontesinos.app_simmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alixmontesinos.app_simmer.ui.navigation.MainScreen
import com.alixmontesinos.app_simmer.ui.screens.Login
import com.alixmontesinos.app_simmer.ui.screens.SplashScreen
import com.alixmontesinos.app_simmer.ui.theme.App_simmerTheme
import com.alixmontesinos.app_simmer.ui.screens.Login






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
                    //Register()
                    //Login()
                    MainScreen()
                    //SplashScreen()

                }

            }
        }
    }
}
