package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.model.Recipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.UUID

/**
 * Estado de la UI para la pantalla de Crear Receta
 */
data class CrearRecetaUiState(
    val titulo: String = "",
    val descripcion: String = "",
    val ingredientes: List<String> = listOf(""),
    val pasos: List<String> = listOf(""),
    val imageUri: Uri? = null,
    val etiquetas: List<String> = emptyList(),
    val tiempoPreparacion: String? = null,
    val dificultad: String? = null,
    val imagenesExtra: List<Uri> = emptyList(),
    val isLoading: Boolean = false,
    val mensajeExito: String? = null,
    val mensajeError: String? = null
)

/**
 * ViewModel para gestionar la creación de recetas.
 * Se ubica en ui.screens.ViewModelScreen siguiendo la estructura actual del proyecto.
 */
class CrearRecetaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CrearRecetaUiState())
    val uiState: StateFlow<CrearRecetaUiState> = _uiState.asStateFlow()

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

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

    // --- Etiquetas y Tiempo y Dificultad ---

    fun toggleEtiqueta(etiqueta: String) {
        val etiquetasActuales = _uiState.value.etiquetas.toMutableList()
        if (etiquetasActuales.contains(etiqueta)) {
            etiquetasActuales.remove(etiqueta)
        } else {
            etiquetasActuales.add(etiqueta)
        }
        _uiState.update { it.copy(etiquetas = etiquetasActuales) }
    }

    fun setTiempoPreparacion(tiempo: String) {
        _uiState.update { it.copy(tiempoPreparacion = tiempo) }
    }

    fun setDificultad(dificultad: String) {
        _uiState.update { it.copy(dificultad = dificultad) }
    }

    // --- Imagenes Extra ---

    fun onExtraImageSelected(uri: Uri?) {
        if (uri != null) {
            val imagenesActuales = _uiState.value.imagenesExtra + uri
            _uiState.update { it.copy(imagenesExtra = imagenesActuales) }
        }
    }

    fun removeExtraImage(index: Int) {
        val imagenesActuales = _uiState.value.imagenesExtra.toMutableList()
        if (index in imagenesActuales.indices) {
            imagenesActuales.removeAt(index)
            _uiState.update { it.copy(imagenesExtra = imagenesActuales) }
        }
    }

    // En CrearRecetaViewModel.kt

    fun guardarReceta() {
        val state = _uiState.value
        val userId = auth.currentUser?.uid

        if (userId == null) {
            _uiState.update { it.copy(mensajeError = "Usuario no autenticado") }
            return
        }

        if (state.titulo.isBlank() || state.descripcion.isBlank() || state.ingredientes.all { it.isBlank() } || state.pasos.all { it.isBlank() }) {
            _uiState.update { it.copy(mensajeError = "Por favor completa los campos obligatorios") }
            return
        }

        if (state.imageUri == null) {
            _uiState.update { it.copy(mensajeError = "Debes seleccionar una imagen principal") }
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            // Solo activamos isLoading. NO tocamos mensajeExito todavía.
            _uiState.update { it.copy(isLoading = true, mensajeError = null, mensajeExito = null) }

            try {
                android.util.Log.d("CrearReceta", "=== INICIANDO ===")

                // 1. Subir imagen principal
                android.util.Log.d("CrearReceta", "Paso 1: Subiendo imagen principal...")
                val filename = UUID.randomUUID().toString()
                val mainImageRef = storage.reference.child("recipes/$userId/$filename.jpg")

                mainImageRef.putFile(state.imageUri).await()
                val mainImageUrl = mainImageRef.downloadUrl.await().toString()

                android.util.Log.d("CrearReceta", "Imagen lista: $mainImageUrl")

                // 2. Subir imágenes extra
                val extraImageUrls = mutableListOf<String>()
                if (state.imagenesExtra.isNotEmpty()) {
                    android.util.Log.d("CrearReceta", "Paso 2: Subiendo extras...")
                    state.imagenesExtra.forEach { uri ->
                        val extraFilename = UUID.randomUUID().toString()
                        val extraRef = storage.reference.child("recipes/$userId/$extraFilename.jpg")
                        extraRef.putFile(uri).await()
                        extraImageUrls.add(extraRef.downloadUrl.await().toString())
                    }
                }

                // 3. Crear objeto
                android.util.Log.d("CrearReceta", "Paso 3: Preparando datos...")
                val recipeId = firestore.collection("recipes").document().id
                val newRecipe = Recipe(
                    id = recipeId,
                    userId = userId,
                    title = state.titulo,
                    author = auth.currentUser?.displayName ?: "Usuario",
                    imageUrl = mainImageUrl,
                    description = state.descripcion,
                    ingredients = state.ingredientes.filter { it.isNotBlank() },
                    steps = state.pasos.filter { it.isNotBlank() },
                    etiquetas = state.etiquetas,
                    tiempoPreparacion = state.tiempoPreparacion ?: "",
                    extraImages = extraImageUrls,
                    time = state.tiempoPreparacion ?: "",
                    difficulty = state.dificultad ?: "Media",
                    calories = "",
                    timestamp = System.currentTimeMillis() // Nuevo campo para ordenamiento
                )

                // 4. Guardar en Firestore
                android.util.Log.d("CrearReceta", "Paso 4: Guardando en Firestore...")
                firestore.collection("recipes").document(recipeId).set(newRecipe).await()

                android.util.Log.d("CrearReceta", "=== TERMINADO CON ÉXITO ===")

                // AHORA SÍ, al final de todo, lanzamos el mensaje de éxito para que la pantalla se cierre.
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        mensajeExito = "¡Receta guardada con éxito!",
                        titulo = "",
                        descripcion = "",
                        ingredientes = listOf(""),
                        pasos = listOf(""),
                        imageUri = null,
                        etiquetas = emptyList(),
                        tiempoPreparacion = null,
                        dificultad = null,
                        imagenesExtra = emptyList()
                    )
                }

            } catch (e: Exception) {
                android.util.Log.e("CrearReceta", "ERROR", e)
                val msg = if (e is CancellationException) {
                    "Carga cancelada. Intenta de nuevo."
                } else {
                    "Error al guardar: ${e.message}"
                }
                _uiState.update { it.copy(isLoading = false, mensajeError = msg) }
            }
        }
    }
}
