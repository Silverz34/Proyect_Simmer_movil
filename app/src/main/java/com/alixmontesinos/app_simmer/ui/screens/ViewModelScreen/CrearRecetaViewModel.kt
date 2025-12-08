package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Estado de la UI para la pantalla de Crear Receta
 */
data class CrearRecetaUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val ingredientes: List<String> = listOf(""),
    val pasos: List<String> = listOf(""),
    val imageUri: Uri? = null
)

/**
 * ViewModel para gestionar la creación de recetas.
 * Se ubica en ui.screens.ViewModelScreen siguiendo la estructura actual del proyecto.
 */
class CrearRecetaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrearRecetaUiState())
    val uiState: StateFlow<CrearRecetaUiState> = _uiState.asStateFlow()

    fun onTituloChange(nuevoTitulo: String) {
        _uiState.update { it.copy(titulo = nuevoTitulo) }
    }

    fun onDescripcionChange(nuevaDescripcion: String) {
        _uiState.update { it.copy(descripcion = nuevaDescripcion) }
    }

    fun onImageSelected(uri: Uri?) {
        _uiState.update { it.copy(imageUri = uri) }
    }

    // --- Ingredientes ---

    fun onIngredienteChange(index: Int, nuevoValor: String) {
        val listaActualizada = _uiState.value.ingredientes.toMutableList()
        if (index in listaActualizada.indices) {
            listaActualizada[index] = nuevoValor
            _uiState.update { it.copy(ingredientes = listaActualizada) }
        }
    }

    fun agregarIngrediente() {
        // Agrega un string vacío para una nueva linea
        val listaActualizada = _uiState.value.ingredientes + ""
        _uiState.update { it.copy(ingredientes = listaActualizada) }
    }

    fun eliminarIngrediente(index: Int) {
        val listaActualizada = _uiState.value.ingredientes.toMutableList()
        if (index in listaActualizada.indices) {
            listaActualizada.removeAt(index)
            _uiState.update { it.copy(ingredientes = listaActualizada) }
        }
    }

    // --- Pasos ---

    fun onPasoChange(index: Int, nuevoValor: String) {
        val listaActualizada = _uiState.value.pasos.toMutableList()
        if (index in listaActualizada.indices) {
            listaActualizada[index] = nuevoValor
            _uiState.update { it.copy(pasos = listaActualizada) }
        }
    }

    fun agregarPaso() {
        val listaActualizada = _uiState.value.pasos + ""
        _uiState.update { it.copy(pasos = listaActualizada) }
    }

    fun eliminarPaso(index: Int) {
        val listaActualizada = _uiState.value.pasos.toMutableList()
        if (index in listaActualizada.indices) {
            listaActualizada.removeAt(index)
            _uiState.update { it.copy(pasos = listaActualizada) }
        }
    }

    fun guardarReceta() {
        // TODO: Implementar lógica de guardado en Firebase/Firestore
        println("Simulando guardado de receta: ${_uiState.value}")
    }
}
