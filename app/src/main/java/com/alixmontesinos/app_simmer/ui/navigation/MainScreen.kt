package com.alixmontesinos.app_simmer.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.BottomNavigationBar
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val pantallasConBarra = listOf(
        items_menu.Home.ruta,
        items_menu.Crear.ruta,
        items_menu.Favorit.ruta,
        items_menu.Perfil.ruta
    )
    Scaffold(
        bottomBar = {
            if (currentRoute in pantallasConBarra){
            BottomNavigationBar(navController = navController)}
        }
    ) { paddingValues ->
        NavHostContainer(
            navController = navController,
            padding = paddingValues
        )
    }
}