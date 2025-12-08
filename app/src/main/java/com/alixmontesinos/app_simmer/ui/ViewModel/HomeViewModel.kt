package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class Category(val name: String, val imageRes: Int)

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageRes: Int,
    val time: String, // e.g., "30 min"
    val difficulty: String // e.g., "Fácil"
)

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _allRecipes = MutableStateFlow<List<Recipe>>(emptyList())

    private val _popularRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val popularRecipes: StateFlow<List<Recipe>> = _popularRecipes

    private val _selectedTime = MutableStateFlow<String?>(null)
    val selectedTime = _selectedTime.asStateFlow()

    private val _selectedDifficulty = MutableStateFlow<String?>(null)
    val selectedDifficulty = _selectedDifficulty.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            delay(2000)
            _categories.value = listOf(
                Category("Cena", R.drawable.category_dinner),
                Category("Comida", R.drawable.category_lunch),
                Category("Desayuno", R.drawable.category_breakfast),
                Category("Postres", R.drawable.category_dessert),
                Category("Snack", R.drawable.category_snack)
            )
            _allRecipes.value = listOf(
                Recipe(1, "Huevito con arroz", "Una pequeña receta...", R.drawable.rigatoni_pasta, "15 min", "Fácil"),
                Recipe(2, "Pollo a la brasa", "Clásico peruano", R.drawable.rigatoni_pasta, "1 h", "Media"),
                Recipe(3, "Lomo Saltado", "Salteado de carne", R.drawable.rigatoni_pasta, "30 min", "Media"),
                Recipe(4, "Causa Limeña", "Plato típico", R.drawable.rigatoni_pasta, "45 min", "Difícil"),
                Recipe(5, "Aji de Gallina", "Cremoso y delicioso", R.drawable.rigatoni_pasta, "1 h", "Media"),
                Recipe(6, "Ceviche", "Pescado fresco marinado", R.drawable.rigatoni_pasta, "30 min", "Fácil")
            )
            _popularRecipes.value = _allRecipes.value
            _isLoading.value = false
        }
    }

    fun onTimeSelected(time: String) {
        _selectedTime.value = time
    }

    fun onDifficultySelected(difficulty: String) {
        _selectedDifficulty.value = difficulty
    }

    fun applyFilters() {
        _popularRecipes.value = _allRecipes.value.filter { recipe ->
            val timeMatch = _selectedTime.value?.let { it == recipe.time } ?: true
            val difficultyMatch = _selectedDifficulty.value?.let { it == recipe.difficulty } ?: true
            timeMatch && difficultyMatch
        }
    }

    fun clearFilters() {
        _selectedTime.value = null
        _selectedDifficulty.value = null
        _popularRecipes.value = _allRecipes.value
    }
}
