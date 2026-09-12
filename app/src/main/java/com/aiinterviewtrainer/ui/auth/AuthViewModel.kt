package com.aiinterviewtrainer.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.model.User
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.AuthResult
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val isNewUser: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            when (val result = authRepository.registerWithEmail(email, password)) {
                is AuthResult.Success -> {
                    val user = User(
                        uid = result.user.uid,
                        name = name,
                        email = email
                    )
                    userRepository.saveUser(user)
                    _uiState.value = AuthUiState(isSuccess = true, isNewUser = true)
                }
                is AuthResult.Error -> {
                    _uiState.value = AuthUiState(error = result.message)
                }
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            when (val result = authRepository.loginWithEmail(email, password)) {
                is AuthResult.Success -> {
                    _uiState.value = AuthUiState(isSuccess = true, isNewUser = false)
                }
                is AuthResult.Error -> {
                    _uiState.value = AuthUiState(error = result.message)
                }
            }
        }
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            when (val result = authRepository.signInWithGoogle(idToken)) {
                is AuthResult.Success -> {
                    val isNew = result.user.metadata?.creationTimestamp ==
                            result.user.metadata?.lastSignInTimestamp
                    if (isNew) {
                        val user = User(
                            uid = result.user.uid,
                            name = result.user.displayName ?: "",
                            email = result.user.email ?: ""
                        )
                        userRepository.saveUser(user)
                    }
                    _uiState.value = AuthUiState(isSuccess = true, isNewUser = isNew == true)
                }
                is AuthResult.Error -> {
                    _uiState.value = AuthUiState(error = result.message)
                }
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            authRepository.sendPasswordReset(email)
            _uiState.value = AuthUiState(error = "Password reset email sent!")
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
