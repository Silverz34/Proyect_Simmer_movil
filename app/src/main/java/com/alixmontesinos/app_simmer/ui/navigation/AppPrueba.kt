package com.alixmontesinos.app_simmer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Login
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Register
import com.alixmontesinos.app_simmer.ui.screens.SplashScreen2
import com.alixmontesinos.app_simmer.ui.screens.UserApp.CrearReceta
import com.alixmontesinos.app_simmer.ui.screens.UserApp.Home
import com.alixmontesinos.app_simmer.ui.screens.UserAuth.Welcome
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.BottomNavigationBar
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.EditarDescrip
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.EditarPerfil
import com.alixmontesinos.app_simmer.ui.screens.PerfilUser.Perfil
import com.alixmontesinos.app_simmer.ui.screens.RecipeDetailScreen
import com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen.DescriptionViewModel


@Composable
fun AppPrueba(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val descriptionViewModel: DescriptionViewModel = viewModel()
    val descripcion by descriptionViewModel.descripcion.collectAsState()

    val bottomBarRoutes = listOf(
        items_menu.Home.ruta,
        items_menu.Crear.ruta,
        items_menu.Favorit.ruta,
        items_menu.Perfil.ruta
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomNavigationBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = OtrasRutas.SplashScreen2.route,
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {

            composable (OtrasRutas.SplashScreen2.route){
                SplashScreen2(navController)
            }
            composable (route = OtrasRutas.Welcome.route){
                Welcome(navController)
            }
            composable(route = OtrasRutas.Login.route){
                Login(navController)
            }
            composable(route = OtrasRutas.Register.route){
                Register(navController)
            }

            // Bottom Bar Routes
            composable(items_menu.Home.ruta) { Home(navController) }
            composable(items_menu.Crear.ruta) { CrearReceta(onBackClick = { navController.popBackStack() }) }
            composable(items_menu.Favorit.ruta) { com.alixmontesinos.app_simmer.ui.screens.UserApp.Favorit(navController) }
            composable(items_menu.Perfil.ruta) {
                Perfil(
                    onEditClick = {navController.navigate(OtrasRutas.EditarPerfil.route)},
                    descripcion
                )
            }


            composable(route = OtrasRutas.EditarPerfil.route) {
                EditarPerfil(
                    currentDescription = descripcion,
                    onDescriptionClick = {navController.navigate(OtrasRutas.EditarDescrip.route)},
                    onBackClick = { navController.popBackStack() }
                )
            }

            composable(route = OtrasRutas.EditarDescrip.route) {
                EditarDescrip(
                    currentDescription = descripcion,
                    onSaveClick = { nuevoTexto ->
                        descriptionViewModel.updateDescripcion(nuevoTexto)
                        navController.popBackStack()
                    },
                    onCancelClick = { navController.popBackStack() }
                )
            }

            composable(
                route = "detail/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
                RecipeDetailScreen(navController, recipeId)
            }
        }
    }
}