package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.data.repository.RecipeRepository
import com.alixmontesinos.app_simmer.data.repository.RecipeRepositoryImpl
import com.alixmontesinos.app_simmer.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepositoryImpl()

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadRecipe(recipeId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _recipe.value = repository.getRecipeById(recipeId)
            _isLoading.value = false
        }
    }
}
