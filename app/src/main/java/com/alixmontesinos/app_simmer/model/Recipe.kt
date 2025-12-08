package com.alixmontesinos.app_simmer.model

import androidx.annotation.DrawableRes

data class Recipe(
    val id: String = "",
    val userId: String = "",
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "", // URL from Firebase Storage
    @DrawableRes val imageRes: Int? = null, // Backward compatibility for local resources
    val time: String = "",
    val difficulty: String = "",
    val calories: String = "",
    val description: String = "",
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val etiquetas: List<String> = emptyList(),
    val tiempoPreparacion: String = "",
    val extraImages: List<String> = emptyList() // URLs
)