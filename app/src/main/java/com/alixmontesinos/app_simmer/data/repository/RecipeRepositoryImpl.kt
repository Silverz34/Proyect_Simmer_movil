package com.alixmontesinos.app_simmer.data.repository

import com.alixmontesinos.app_simmer.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RecipeRepositoryImpl : RecipeRepository {
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getRecipes(): List<Recipe> {
        return try {
            val snapshot = firestore.collection("recipes").get().await()
            android.util.Log.d("RecipeRepository", "Found ${snapshot.size()} documents")
            val recipes = snapshot.documents.mapNotNull { document ->
                val recipe = document.toObject(Recipe::class.java)
                recipe?.copy(id = document.id)
            }
            recipes
        } catch (e: Exception) {
            android.util.Log.e("RecipeRepository", "Error fetching recipes", e)
            emptyList()
        }
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        return try {
            val document = firestore.collection("recipes").document(id).get().await()
            if (document.exists()) {
                document.toObject(Recipe::class.java)
            } else {
                null
            }
        } catch (e: Exception) {
            android.util.Log.e("RecipeRepository", "Error fetching recipe $id", e)
            e.printStackTrace()
            null
        }
    }
}
