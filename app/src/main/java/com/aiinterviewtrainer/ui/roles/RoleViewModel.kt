package com.aiinterviewtrainer.ui.roles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

val POPULAR_ROLES = listOf(
    "Software Developer", "Frontend Developer", "Backend Developer",
    "Full Stack Developer", "Android Developer", "iOS Developer",
    "Data Scientist", "Data Analyst", "ML Engineer",
    "DevOps Engineer", "Cloud Engineer", "QA Engineer",
    "Product Manager", "UI/UX Designer", "Business Analyst",
    "System Administrator", "Cybersecurity Analyst", "Database Administrator"
)

data class RoleUiState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val selectedRoles: Set<String> = emptySet(),
    val error: String? = null
)

@HiltViewModel
class RoleViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RoleUiState())
    val uiState: StateFlow<RoleUiState> = _uiState

    init { loadExistingRoles() }

    private fun loadExistingRoles() {
        viewModelScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            userRepository.getUser(uid).onSuccess { user ->
                _uiState.value = _uiState.value.copy(selectedRoles = user.selectedRoles.toSet())
            }
        }
    }

    fun toggleRole(role: String) {
        val current = _uiState.value.selectedRoles.toMutableSet()
        if (current.contains(role)) current.remove(role) else current.add(role)
        _uiState.value = _uiState.value.copy(selectedRoles = current)
    }

    fun saveRoles() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val uid = authRepository.currentUser?.uid ?: return@launch
            userRepository.updateUser(uid, mapOf("selectedRoles" to _uiState.value.selectedRoles.toList()))
                .fold(
                    onSuccess = { _uiState.value = _uiState.value.copy(isSaved = true, isLoading = false) },
                    onFailure = { _uiState.value = _uiState.value.copy(error = it.message, isLoading = false) }
                )
        }
    }
}
