package com.alixmontesinos.app_simmer.ui.navigation

import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alixmontesinos.app_simmer.ui.screens.Crear
import com.alixmontesinos.app_simmer.ui.screens.Favorit
import com.alixmontesinos.app_simmer.ui.screens.Home
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.Perfil

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = items_menu.Home.ruta
    ) {
        composable(items_menu.Home.ruta) {
            Home()
        }
        composable(items_menu.Crear.ruta) {
            Crear()
        }
        composable(items_menu.Favorit.ruta) {
            Favorit()
        }
        composable(items_menu.Perfil.ruta) {
            Perfil(onEditClick = {})
        }
    }
}