package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Category(val name: String, val imageRes: Int)
data class Recipe(val name: String)

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

            _popularRecipes.value = listOf(
                Recipe("Huevito con arroz"),
                Recipe("Pollo a la brasa"),
                Recipe("Lomo Saltado"),
                Recipe("Causa Limeña"),
                Recipe("Aji de Gallina"),
                Recipe("Ceviche")
            )

            _isLoading.value = false
        }
    }
}