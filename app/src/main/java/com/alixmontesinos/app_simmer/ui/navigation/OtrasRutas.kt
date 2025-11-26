package com.alixmontesinos.app_simmer.ui.navigation

import com.alixmontesinos.app_simmer.ui.components.BottomNavigation.items_menu

sealed class OtrasRutas(val route:String){
    object  EditarPerfil:OtrasRutas ("EditPerfil")
    object SplashScreen2:OtrasRutas ("SplashScreen2")
    object Welcome:OtrasRutas ("Welcome")
    object Login:OtrasRutas ("Login")
    object Register: OtrasRutas("Register")
}