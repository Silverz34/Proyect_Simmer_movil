package com.alixmontesinos.app_simmer.model

data class perfilUser(
    val username: String = "",
    val email: String = "",
    val description: String = "",
    val photoUrl: String = "",
    val isLoading: Boolean = false
)
