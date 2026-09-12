package com.aiinterviewtrainer.ui.profile

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiinterviewtrainer.data.local.FileStorageManager
import com.aiinterviewtrainer.data.model.User
import com.aiinterviewtrainer.data.repository.AuthRepository
import com.aiinterviewtrainer.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null,
    val user: User? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val fileStorageManager: FileStorageManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init { loadUser() }

    private fun loadUser() {
        viewModelScope.launch {
            val uid = authRepository.currentUser?.uid ?: return@launch
            userRepository.getUser(uid).onSuccess {
                _uiState.value = _uiState.value.copy(user = it)
            }
        }
    }

    fun saveProfile(
        name: String,
        college: String,
        graduationYear: String,
        phone: String,
        photoBitmap: Bitmap?
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val uid = authRepository.currentUser?.uid ?: return@launch

            var photoPath = _uiState.value.user?.profilePhotoPath ?: ""
            if (photoBitmap != null) {
                photoPath = fileStorageManager.saveProfilePhoto(photoBitmap, uid)
            }

            val user = User(
                uid = uid,
                name = name,
                email = authRepository.currentUser?.email ?: "",
                college = college,
                graduationYear = graduationYear,
                phone = phone,
                profilePhotoPath = photoPath,
                resumePath = _uiState.value.user?.resumePath ?: "",
                selectedRoles = _uiState.value.user?.selectedRoles ?: emptyList()
            )
            userRepository.saveUser(user).fold(
                onSuccess = { _uiState.value = ProfileUiState(isSaved = true, user = user) },
                onFailure = { _uiState.value = ProfileUiState(error = it.message) }
            )
        }
    }
}
