package com.aiinterviewtrainer.ui.splash

import androidx.lifecycle.ViewModel
import com.aiinterviewtrainer.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(authRepository.isLoggedIn)
    val isLoggedIn = _isLoggedIn.asStateFlow()
}
