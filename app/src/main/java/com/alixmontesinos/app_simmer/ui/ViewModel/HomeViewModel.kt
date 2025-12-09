package com.alixmontesinos.app_simmer.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.R
import com.alixmontesinos.app_simmer.model.Recipe
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class Category(val name: String, val imageRes: Int)

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
    
    // Firestore instance
    private val firestore = FirebaseFirestore.getInstance()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            _categories.value = listOf(
                Category("Cena", R.drawable.category_breakfast),
                Category("Comida", R.drawable.category_dinner),
                Category("Desayuno", R.drawable.category_lunch),
                Category("Postres", R.drawable.category_dessert),
                Category("Snack", R.drawable.category_snack)
            )

            // Fetch from Firestore with real-time updates
            firestore.collection("recipes")
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        _isLoading.value = false
                        return@addSnapshotListener
                    }

                    if (snapshot != null) {
                        val recipes = snapshot.toObjects(Recipe::class.java)
                        _allRecipes.value = recipes
                        _popularRecipes.value = recipes
                        _isLoading.value = false
                    }
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
