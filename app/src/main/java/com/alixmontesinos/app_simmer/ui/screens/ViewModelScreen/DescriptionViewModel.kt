package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alixmontesinos.app_simmer.model.perfilUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    private val _uiState = MutableStateFlow(perfilUser())
    val uiState: StateFlow<perfilUser> = _uiState.asStateFlow()
    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            _uiState.value = _uiState.value.copy(isLoading = true)
            firestore.collection("users").document(currentUser.uid)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        return@addSnapshotListener
                    }

                    if (snapshot != null && snapshot.exists()) {
                        val username = snapshot.getString("username") ?: ""
                        val description = snapshot.getString("description") ?: "Sin descripción"
                        val photoUrl = snapshot.getString("photoUrl") ?: ""
                        val email = snapshot.getString("email") ?: ""

                        _uiState.value = _uiState.value.copy(
                            username = username,
                            description = description,
                            photoUrl = photoUrl,
                            email = email,
                            isLoading = false
                        )
                    }
                }
        }
    }

    fun updateDescription(newDescription: String) {
        val uid = auth.currentUser?.uid ?: return

        viewModelScope.launch {
            try {
                firestore.collection("users").document(uid)
                    .update("description", newDescription).await()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    description = _uiState.value.description // mantener el valor anterior
                )
            }
        }
    }

    fun uploadProfilePicture(imageUri: Uri) {
        val uid = auth.currentUser?.uid ?: return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val storageRef = storage.reference.child("profile_images/$uid.jpg")
                storageRef.putFile(imageUri).await()
                val downloadUrl = storageRef.downloadUrl.await().toString()
                firestore.collection("users").document(uid)
                    .update("photoUrl", downloadUrl).await()

            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error al subir la foto de perfil", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}