package com.alixmontesinos.app_simmer.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alixmontesinos.app_simmer.ui.screens.Login
import com.alixmontesinos.app_simmer.ui.screens.Register
import com.alixmontesinos.app_simmer.ui.screens.SplashScreen
import com.alixmontesinos.app_simmer.ui.screens.SplashScreen2
import com.alixmontesinos.app_simmer.ui.screens.Welcome

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
    }
}