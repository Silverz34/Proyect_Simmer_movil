package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Category(val name: String, val imageRes: Int)

// Updated Recipe data class for Home screen
data class Recipe(
    val name: String,
    val description: String,
    val imageRes: Int // We'll use a placeholder for now
)

class HomeViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _popularRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val popularRecipes: StateFlow<List<Recipe>> = _popularRecipes

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            // Simulate network delay
            delay(2000)

            _categories.value = listOf(
                Category("Cena", R.drawable.category_dinner),
                Category("Comida", R.drawable.category_lunch),
                Category("Desayuno", R.drawable.category_breakfast),
                Category("Postres", R.drawable.category_dessert),
                Category("Snack", R.drawable.category_snack)
            )

            // Updated popular recipes with more data
            _popularRecipes.value = listOf(
                Recipe("Huevito con arroz", "Una pequeña receta...", R.drawable.rigatoni_pasta),
                Recipe("Pollo a la brasa", "Clásico peruano", R.drawable.rigatoni_pasta),
                Recipe("Lomo Saltado", "Salteado de carne", R.drawable.rigatoni_pasta),
                Recipe("Causa Limeña", "Plato típico", R.drawable.rigatoni_pasta),
                Recipe("Aji de Gallina", "Cremoso y delicioso", R.drawable.rigatoni_pasta),
                Recipe("Ceviche", "Pescado fresco marinado", R.drawable.rigatoni_pasta)
            )

            _isLoading.value = false
        }
    }
}