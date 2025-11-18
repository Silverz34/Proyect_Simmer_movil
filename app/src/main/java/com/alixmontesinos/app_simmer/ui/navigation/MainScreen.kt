package com.alixmontesinos.app_simmer.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.BottomNavigationBar

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        NavHostContainer(
            navController = navController,
            padding = paddingValues
        )
    }
}