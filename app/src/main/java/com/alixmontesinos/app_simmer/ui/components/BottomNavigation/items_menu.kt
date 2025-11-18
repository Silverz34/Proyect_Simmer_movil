package com.alixmontesinos.app_simmer.ui.components.BottomNavigation

import com.alixmontesinos.app_simmer.R

sealed class items_menu(
    val icon: Int,
    val title: String,
    val ruta: String
){
    object Home: items_menu(
        R.drawable.icon_home,
        "Home", "Home")
    object Crear: items_menu(
        R.drawable.icon_add,
        "Crear", "Crear"
    )
    object Favorit: items_menu(
        R.drawable.icon_bookmark,
        "Favorit", "Favorit"
    )
    object Perfil: items_menu(
        R.drawable.icon_user,
        "Perfil", "Perfil"
    )
}
val items = listOf(
    items_menu.Home,
    items_menu.Crear,
    items_menu.Favorit,
    items_menu.Perfil,
)