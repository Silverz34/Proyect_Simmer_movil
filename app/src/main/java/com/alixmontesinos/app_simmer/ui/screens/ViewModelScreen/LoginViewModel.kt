package com.alixmontesinos.app_simmer.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    // ─────────────────────────────────────────────
    // Actualizar campos
    // ─────────────────────────────────────────────

    fun onUsernameChange(newValue: String) {
        uiState = uiState.copy(
            username = newValue,
            usernameError = validateUsername(newValue)
        )
    }

    fun onPasswordChange(newValue: String) {
        uiState = uiState.copy(
            password = newValue,
            passwordError = validatePassword(newValue)
        )
    }

    // ─────────────────────────────────────────────
    // Validaciones
    // ─────────────────────────────────────────────

    private fun validateUsername(username: String): String? {
        return when {
            username.isBlank() -> "El usuario no puede estar vacío"
            username.length < 4 -> "Mínimo 4 caracteres"
            else -> null
        }
    }

    private fun validatePassword(password: String): String? {
        return when {
            password.isBlank() -> "La contraseña no puede estar vacía"
            password.length < 6 -> "Mínimo 6 caracteres"
            else -> null
        }
    }

    // ¿Está todo listo para intentar login?
    fun isFormValid(): Boolean {
        return uiState.usernameError == null &&
                uiState.passwordError == null &&
                uiState.username.isNotBlank() &&
                uiState.password.isNotBlank()
    }

    // ─────────────────────────────────────────────
    // Intento de login
    // ─────────────────────────────────────────────
    /**
     * Valida ambos campos y regresa:
     *  - true  → si todo está correcto (aquí podrías llamar a backend)
     *  - false → si hay errores (usernameError / passwordError quedan llenos)
     */
    fun tryLogin(): Boolean {
        val usernameError = validateUsername(uiState.username)
        val passwordError = validatePassword(uiState.password)

        uiState = uiState.copy(
            usernameError = usernameError,
            passwordError = passwordError
        )

        // aquí podrías meter lógica real: llamada a API, Firebase, etc.
        // por ahora solo validamos formato
        return usernameError == null && passwordError == null
    }
}
