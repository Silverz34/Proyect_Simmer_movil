package com.alixmontesinos.app_simmer.data.repository

import com.alixmontesinos.app_simmer.data.local.FavoriteDao
import com.alixmontesinos.app_simmer.data.local.FavoriteEntity
import com.alixmontesinos.app_simmer.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FavoriteRepository {
    fun getFavorites(): Flow<List<Recipe>>
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun addFavorite(recipe: Recipe)
    suspend fun removeFavorite(id: String)
}

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override fun getFavorites(): Flow<List<Recipe>> {
        return favoriteDao.getFavorites().map { entities ->
            entities.map { entity ->
                Recipe(
                    id = entity.id,
                    title = entity.title,
                    author = entity.author,
                    imageUrl = entity.imageUrl, // Map back
                    imageRes = entity.imageRes,
                    time = entity.time,
                    difficulty = entity.difficulty,
                    calories = entity.calories,
                    description = entity.description,
                    ingredients = entity.ingredients,
                    steps = entity.steps
                )
            }
        }
    }

    override fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id)

    override suspend fun addFavorite(recipe: Recipe) {
        val entity = FavoriteEntity(
            id = recipe.id,
            title = recipe.title,
            author = recipe.author,
            imageUrl = recipe.imageUrl,
            imageRes = recipe.imageRes,
            time = recipe.time,
            difficulty = recipe.difficulty,
            calories = recipe.calories,
            description = recipe.description,
            ingredients = recipe.ingredients,
            steps = recipe.steps
        )
        favoriteDao.insertFavorite(entity)
    }

    override suspend fun removeFavorite(id: String) {
        favoriteDao.deleteFavoriteById(id)
    }
}
