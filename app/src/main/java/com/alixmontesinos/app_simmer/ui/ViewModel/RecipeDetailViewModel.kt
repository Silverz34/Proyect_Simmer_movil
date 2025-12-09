package com.alixmontesinos.app_simmer.ui.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.data.local.AppDatabase
import com.alixmontesinos.app_simmer.data.repository.FavoriteRepository
import com.alixmontesinos.app_simmer.data.repository.FavoriteRepositoryImpl
import com.alixmontesinos.app_simmer.data.repository.RecipeRepository
import com.alixmontesinos.app_simmer.data.repository.RecipeRepositoryImpl
import com.alixmontesinos.app_simmer.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: RecipeRepository = RecipeRepositoryImpl()
    private val favoriteRepository: FavoriteRepository

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        val database = AppDatabase.getDatabase(application)
        favoriteRepository = FavoriteRepositoryImpl(database.favoriteDao())
    }

    fun loadRecipe(recipeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _recipe.value = repository.getRecipeById(recipeId)
            _isLoading.value = false
            
            // Check if favorite
            favoriteRepository.isFavorite(recipeId).collect { isFav ->
                _isFavorite.value = isFav
            }
        }
    }

    fun toggleFavorite() {
        val currentRecipe = _recipe.value ?: return
        viewModelScope.launch {
            if (_isFavorite.value) {
                favoriteRepository.removeFavorite(currentRecipe.id)
            } else {
                favoriteRepository.addFavorite(currentRecipe)
            }
        }
    }
}
