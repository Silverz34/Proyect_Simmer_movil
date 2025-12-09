package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class Category(val name: String, val imageRes: Int)

class HomeViewModel : ViewModel() {

    // private val repository: RecipeRepository = RecipeRepositoryImpl() // Removed as we use Firestore directly

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

    private val firestore = FirebaseFirestore.getInstance()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _categories.value = listOf(
                Category("Cena", R.drawable.category_dinner),
                Category("Comida", R.drawable.category_lunch),
                Category("Desayuno", R.drawable.category_breakfast),
                Category("Postres", R.drawable.category_dessert),
                Category("Snack", R.drawable.category_snack)
            )
        }

        // Fetch from Firestore with real-time updates
        firestore.collection("recipes")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    _isLoading.value = false
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val recipes = snapshot.toObjects(Recipe::class.java)
                    // Ordenar localmente para manejar recetas antiguas sin timestamp (default 0L)
                    val sortedRecipes = recipes.sortedByDescending { it.timestamp }
                    
                    _allRecipes.value = sortedRecipes
                    _popularRecipes.value = sortedRecipes
                    _isLoading.value = false
                }
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
