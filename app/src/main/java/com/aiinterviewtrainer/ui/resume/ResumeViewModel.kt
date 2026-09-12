package com.aiinterviewtrainer.ui.resume

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.local.FileStorageManager
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.GeminiRepository
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ResumeState(
    val isLoading: Boolean = false,
    val resumeSaved: Boolean = false,
    val analysisComplete: Boolean = false,
    val extractedSkills: List<String> = emptyList(),
    val suggestedRoles: List<String> = emptyList(),
    val experienceLevel: String = "",
    val summary: String = "",
    val error: String? = null
)

@HiltViewModel
class ResumeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val fileStorageManager: FileStorageManager,
    private val geminiRepository: GeminiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResumeState())
    val uiState = _uiState.asStateFlow()

    fun saveResume(uri: Uri, extractedText: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                resumeSaved = false,
                analysisComplete = false
            )

            val uid = authRepository.currentUser?.uid ?: run {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Not logged in"
                )
                return@launch
            }

            try {
                val path = fileStorageManager.saveResume(uri, uid)
                userRepository.updateResumePath(uid, path)

                _uiState.value = _uiState.value.copy(
                    resumeSaved = true
                )

                runResumeAnalysis(uid)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to save resume"
                )
            }
        }
    }

    fun analyzeResume() {
        viewModelScope.launch {
            val uid = authRepository.currentUser?.uid ?: run {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Not logged in"
                )
                return@launch
            }

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                analysisComplete = false
            )

            runResumeAnalysis(uid)
        }
    }

    private suspend fun runResumeAnalysis(uid: String) {
        try {
            val bitmap = fileStorageManager.getResumeBitmap(uid)

            geminiRepository.analyzeResume(bitmap)
                .onSuccess { result ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        analysisComplete = true,
                        extractedSkills = result.skills,
                        suggestedRoles = result.recommendedRoles,
                        experienceLevel = result.experienceLevel,
                        summary = result.summary,
                        error = null
                    )

                    userRepository.updateSelectedRoles(uid, result.recommendedRoles)
                }
                .onFailure { e ->
                    val message = e.message.orEmpty()

                    val shouldFallback =
                        message.contains("quota", ignoreCase = true) ||
                                message.contains("rate limit", ignoreCase = true) ||
                                message.contains("429", ignoreCase = true) ||
                                message.contains("resource_exhausted", ignoreCase = true) ||
                                message.contains("not found", ignoreCase = true) ||
                                message.contains("unsupported", ignoreCase = true)

                    if (shouldFallback) {
                        val fallbackRoles = listOf(
                            "Software Developer",
                            "Frontend Developer",
                            "Android Developer",
                            "Web Developer",
                            "QA Engineer"
                        )

                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            analysisComplete = true,
                            extractedSkills = listOf("Communication", "Problem Solving"),
                            suggestedRoles = fallbackRoles,
                            experienceLevel = "fresher",
                            summary = "AI resume analysis is temporarily unavailable. Default role suggestions are shown so you can continue.",
                            error = null
                        )

                        userRepository.updateSelectedRoles(uid, fallbackRoles)
                    } else {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            analysisComplete = false,
                            error = e.message ?: "Analysis failed"
                        )
                    }
                }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                analysisComplete = false,
                error = e.message ?: "Analysis failed"
            )
        }
    }

    fun resetState() {
        _uiState.value = ResumeState()
    }
}