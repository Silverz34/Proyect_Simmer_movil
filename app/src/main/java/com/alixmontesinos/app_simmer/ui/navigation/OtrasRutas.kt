package com.alixmontesinos.app_simmer.ui.navigation

sealed class OtrasRutas(val route: String) {
    object EditarPerfil : OtrasRutas("EditPerfil")
    object SplashScreen2 : OtrasRutas("SplashScreen2")
    object Welcome : OtrasRutas("Welcome")
    object Login : OtrasRutas("Login")
    object Register : OtrasRutas("Register")

    // Ruta de detalle actualizada para aceptar un ID
    object RecipeDetail : OtrasRutas("RecipeDetail/{recipeId}") {
        fun createRoute(recipeId: Int) = "RecipeDetail/$recipeId"
    }
}