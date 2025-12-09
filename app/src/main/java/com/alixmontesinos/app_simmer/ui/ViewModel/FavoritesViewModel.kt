package com.alixmontesinos.app_simmer.ui.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.data.local.AppDatabase
import com.alixmontesinos.app_simmer.data.repository.FavoriteRepository
import com.alixmontesinos.app_simmer.data.repository.FavoriteRepositoryImpl
import com.alixmontesinos.app_simmer.model.Recipe
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteRepository: FavoriteRepository

    val favorites: StateFlow<List<Recipe>>

    init {
        val database = AppDatabase.getDatabase(application)
        favoriteRepository = FavoriteRepositoryImpl(database.favoriteDao())
        
        favorites = favoriteRepository.getFavorites()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )
    }
}
