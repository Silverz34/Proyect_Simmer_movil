package com.alixmontesinos.app_simmer.domain.model

// app/src/main/java/com/alixmontesinos/app_simmer/model/Recipe.kt

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