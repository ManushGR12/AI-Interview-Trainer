package com.aiinterviewtrainer.ui.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.model.Session
import com.aiinterviewtrainer.data.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SessionDetailUiState(
    val isLoading: Boolean = false,
    val session: Session? = null,
    val error: String? = null
)

@HiltViewModel
class SessionDetailViewModel @Inject constructor(
    private val sessionRepository: SessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(SessionDetailUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    private val sessionId: String = savedStateHandle["sessionId"] ?: ""

    init {
        loadSession()
    }

    fun loadSession() {
        if (sessionId.isBlank()) {
            _uiState.value = SessionDetailUiState(
                isLoading = false,
                error = "Invalid session id"
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            sessionRepository.getSessionById(sessionId)
                .onSuccess { session ->
                    _uiState.value = SessionDetailUiState(
                        isLoading = false,
                        session = session
                    )
                }
                .onFailure { e ->
                    _uiState.value = SessionDetailUiState(
                        isLoading = false,
                        error = e.message ?: "Failed to load session"
                    )
                }
        }
    }
}