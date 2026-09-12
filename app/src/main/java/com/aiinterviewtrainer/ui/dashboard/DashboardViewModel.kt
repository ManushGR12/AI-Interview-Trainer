package com.aiinterviewtrainer.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.data.model.User
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.SessionRepository
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val recentSessions: List<Session> = emptyList(),
    val averageScore: Int = 0,
    val totalSessions: Int = 0,
    val bestScore: Int = 0
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboard()
    }

    fun loadDashboard() {
        viewModelScope.launch {
            val uid = authRepository.currentUser?.uid ?: run {
                _uiState.value = DashboardUiState(isLoading = false)
                return@launch
            }

            _uiState.value = _uiState.value.copy(isLoading = true)

            val user = userRepository.getUser(uid).getOrNull()
            val sessions = sessionRepository.getSessionsForUser(uid).getOrDefault(emptyList())

            val avg = if (sessions.isNotEmpty()) {
                sessions.map { it.overallScore }.average().toInt()
            } else {
                0
            }

            val best = sessions.maxOfOrNull { it.overallScore } ?: 0

            _uiState.value = DashboardUiState(
                isLoading = false,
                user = user,
                recentSessions = sessions.take(5),
                averageScore = avg,
                totalSessions = sessions.size,
                bestScore = best
            )
        }
    }

    fun signOut() {
        authRepository.signOut()
    }
}