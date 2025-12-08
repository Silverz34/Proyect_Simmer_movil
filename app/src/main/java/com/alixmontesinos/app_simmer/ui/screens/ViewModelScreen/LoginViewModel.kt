package com.alixmontesinos.app_simmer.ui.screens.ViewModelScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val loginError: String? = null,
    val isSuccess: Boolean = false
)

class  LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // ─────────────────────────────────────────────
    // Actualizar campos
    // ─────────────────────────────────────────────

    fun onUsernameChange(newValue: String) {
        uiState = uiState.copy(
            username = newValue,
            usernameError = null,
            loginError = null
        )
    }

    fun onPasswordChange(newValue: String) {
        uiState = uiState.copy(
            password = newValue,
            passwordError = null,
            loginError = null
        )
    }

    // ─────────────────────────────────────────────
    // Validaciones
    // ─────────────────────────────────────────────

    private fun validateFields(): Boolean {
        val usernameError = if (uiState.username.isBlank()) "El correo no puede estar vacío" else null
        val passwordError = if (uiState.password.isBlank()) "La contraseña no puede estar vacía" else null

        uiState = uiState.copy(
            usernameError = usernameError,
            passwordError = passwordError
        )

        return usernameError == null && passwordError == null
    }

    fun isFormValid(): Boolean {
        return uiState.username.isNotBlank() && uiState.password.isNotBlank()
    }

    // ─────────────────────────────────────────────
    // Intento de login
    // ─────────────────────────────────────────────
    fun tryLogin() {
        if (!validateFields()) return

        uiState = uiState.copy(isLoading = true, loginError = null)

        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(uiState.username, uiState.password).await()
                uiState = uiState.copy(isLoading = false, isSuccess = true)
            } catch (e: Exception) {
                val errorMsg = when (e) {
                    is FirebaseAuthInvalidUserException -> "Usuario no encontrado"
                    is FirebaseAuthInvalidCredentialsException -> "Credenciales incorrectas"
                    else -> "Error al iniciar sesión: ${e.message}"
                }
                uiState = uiState.copy(isLoading = false, loginError = errorMsg)
            }
        }
    }
    
    fun onLoginSuccessConsumed() {
        uiState = uiState.copy(isSuccess = false)
    }
}
