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

@Composable
fun AppPrueba(){
    val navController= rememberNavController()
    NavHost(navController= navController, startDestination = OtrasRutas.SplashScreen2.route){

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

        composable (route= OtrasRutas.CrearReceta.route){
            CrearReceta(onBackClick = { navController.popBackStack() })
        }

        composable (route= OtrasRutas.Home.route){
            Home(onBackClick = { navController.popBackStack() })
        }

    }
}