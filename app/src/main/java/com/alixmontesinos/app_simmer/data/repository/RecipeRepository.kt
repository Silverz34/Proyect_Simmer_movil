package com.alixmontesinos.app_simmer.data.repository

import com.alixmontesinos.app_simmer.model.Recipe

interface RecipeRepository {
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipeById(id: String): Recipe?
}
