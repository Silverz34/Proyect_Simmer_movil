package com.alixmontesinos.app_simmer.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu
import com.alixmontesinos.app_simmer.ui.screens.UserApp.CrearReceta
import com.alixmontesinos.app_simmer.ui.screens.UserApp.Favorit
import com.alixmontesinos.app_simmer.ui.screens.UserApp.Home
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Login
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.EditarPerfil
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.Perfil
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Register
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Welcome
import com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen.CrearRecetaViewModel

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {
    Box(modifier = Modifier.padding(padding)) {
        NavHost(
            navController = navController,
            startDestination = OtrasRutas.Welcome.route
        ) {
            //navegacion sin bottombar
            composable(route = OtrasRutas.Welcome.route) {
                Welcome(navController)
            }

            composable(route = OtrasRutas.Login.route) {
                Login(navController)
            }
            composable(route = OtrasRutas.Register.route) {
                Register(navController)
            }

            //vistas con BottomBar
            composable(items_menu.Home.ruta) { Home() }
            composable(items_menu.Crear.ruta) { 
                CrearReceta(
                    onBackClick = { navController.popBackStack() }
                ) 
            }
            composable(items_menu.Favorit.ruta) { Favorit() }
            composable(items_menu.Perfil.ruta) {
                Perfil(
                    onEditClick = { navController.navigate(OtrasRutas.EditarPerfil.route) }
                )
            }

            composable(route = OtrasRutas.EditarPerfil.route) {
                EditarPerfil(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
