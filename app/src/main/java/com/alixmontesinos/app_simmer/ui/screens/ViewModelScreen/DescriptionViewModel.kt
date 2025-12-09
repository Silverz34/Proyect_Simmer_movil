package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import android.accessibilityservice.GestureDescription
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DescriptionViewModel : ViewModel()  {
    private val _descripcion = MutableStateFlow("descrpccion de perfil")
    val descripcion: StateFlow<String> = _descripcion.asStateFlow()
    fun updateDescripcion(newDescription: String){
        _descripcion.value = newDescription
    }
}