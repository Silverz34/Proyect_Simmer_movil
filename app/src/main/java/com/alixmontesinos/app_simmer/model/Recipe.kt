package com.alixmontesinos.app_simmer.model

import androidx.annotation.DrawableRes

data class Recipe(
    val id: Int,
    val title: String,
    val author: String,
    @DrawableRes val imageRes: Int,
    val time: String, // Ej: "45 min"
    val difficulty: String, // Ej: "Medium"
    val calories: String, // Ej: "350 kcal"
    val description: String,
    val ingredients: List<String>,
    val steps: List<String>
)