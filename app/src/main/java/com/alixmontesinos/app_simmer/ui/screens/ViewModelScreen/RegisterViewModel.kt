package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class RegisterUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val usernameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val isLoading: Boolean = false,
    val registerError: String? = null,
    val isSuccess: Boolean = false
)

class RegisterViewModel : ViewModel() {

    var uiState by mutableStateOf(RegisterUiState())
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    // ─────────────────────────────────────────────
    // Actualizar campos
    // ─────────────────────────────────────────────

    fun onUsernameChange(newValue: String) {
        uiState = uiState.copy(username = newValue, usernameError = null)
    }

    fun onEmailChange(newValue: String) {
        uiState = uiState.copy(email = newValue, emailError = null)
    }

    fun onPasswordChange(newValue: String) {
        uiState = uiState.copy(password = newValue, passwordError = null)
    }
    
    fun onConfirmPasswordChange(newValue: String) {
        uiState = uiState.copy(confirmPassword = newValue, confirmPasswordError = null)
    }

    // ─────────────────────────────────────────────
    // Validaciones
    // ─────────────────────────────────────────────

    private fun validateFields(): Boolean {
        val usernameError = if (uiState.username.isBlank()) "El usuario es requerido" else null
        val emailError = if (uiState.email.isBlank()) "El correo es requerido" else null
        val passwordError = if (uiState.password.length < 6) "La contraseña debe tener al menos 6 caracteres" else null
        val confirmError = if (uiState.confirmPassword != uiState.password) "Las contraseñas no coinciden" else null

        uiState = uiState.copy(
            usernameError = usernameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmError
        )

        return usernameError == null && emailError == null && passwordError == null && confirmError == null
    }

    // ─────────────────────────────────────────────
    // Registro
    // ─────────────────────────────────────────────
    fun tryRegister() {
        if (!validateFields()) return

        uiState = uiState.copy(isLoading = true, registerError = null)

        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            try {
                // 1. Crear usuario en Firebase Auth
                val authResult = auth.createUserWithEmailAndPassword(uiState.email, uiState.password).await()
                val user = authResult.user

                if (user != null) {
                    // 2. Guardar datos adicionales en Firestore
                    val userData = hashMapOf(
                        "uid" to user.uid,
                        "username" to uiState.username,
                        "email" to uiState.email,
                        "createdAt" to System.currentTimeMillis()
                    )

                    firestore.collection("users").document(user.uid).set(userData).await()

                    uiState = uiState.copy(isLoading = false, isSuccess = true)
                } else {
                    uiState = uiState.copy(isLoading = false, registerError = "Error desconocido al crear usuario")
                }

            } catch (e: Exception) {
                val errorMsg = when (e) {
                    is FirebaseAuthUserCollisionException -> "El correo ya está registrado"
                    else -> "Error al registrar: ${e.message}"
                }
                uiState = uiState.copy(isLoading = false, registerError = errorMsg)
            }
        }
    }
    
    fun onRegisterSuccessConsumed() {
        uiState = uiState.copy(isSuccess = false)
    }
}
