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
import com.alixmontesinos.app_simmer.ui.navigation.OtrasRutas
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.BottomNavigationBar
import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu
import com.alixmontesinos.app_simmer.ui.screens.RecipeDetailScreen



@Composable
fun AppPrueba(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = listOf(
        items_menu.Home.ruta,
        items_menu.Crear.ruta,
        items_menu.Favorit.ruta,
        items_menu.Perfil.ruta
    )

    androidx.compose.material3.Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                com.alixmontesinos.app_simmer.ui.components.BottomNavigation.BottomNavigationBar(navController)
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



            composable (route= OtrasRutas.Home.route){
                Home(navController)
            }
            
            // Bottom Bar Routes
            composable(items_menu.Home.ruta) { Home(navController) }
            composable(items_menu.Crear.ruta) { CrearReceta(onBackClick = { navController.popBackStack() }) } // Using actual screen
            composable(items_menu.Favorit.ruta) { com.alixmontesinos.app_simmer.ui.screens.UserApp.Favorit(navController) }
            composable(items_menu.Perfil.ruta) {
                com.alixmontesinos.app_simmer.ui.screens.PerfilUser.Perfil(
                    onEditClick = { navController.navigate(OtrasRutas.EditarPerfil.route) }
                )
            }


            // PRO TIP: Removing duplicate routes if any exist in the original block
            // However, looking at original file, "OtrasRutas.Home" was mapped to Home(navController)
            // and items_menu.Home.ruta is "Home".
            // OtrasRutas.Home.route needs to differ or match. "Home" vs "home" vs "items_menu.Home.ruta".
            // Assuming OtrasRutas.Home.route is "Home" same as items_menu.Home.ruta.
            
            composable (route = OtrasRutas.EditarPerfil.route) {
                com.alixmontesinos.app_simmer.ui.screens.PerfilUser.EditarPerfil(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // Dentro de tu NavHost
            composable(
                route = OtrasRutas.RecipeDetail.route, // Definimos que acepta un parámetro
                arguments = listOf(navArgument("recipeId") { type = androidx.navigation.NavType.StringType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
                RecipeDetailScreen(navController, recipeId)
            }
        }
    }
}