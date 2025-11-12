package com.cornellappdev.hustle.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.repository.auth.AuthRepository
import com.cornellappdev.hustle.data.repository.auth.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RootUiState(
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = true,
)

@HiltViewModel
class RootViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : HustleViewModel<RootUiState>(
    initialUiState = RootUiState()
) {
    init {
        collectUserSignInStatus()
        collectSessionExpired()
    }

    private fun collectUserSignInStatus() {
        viewModelScope.launch {
            authRepository.currentUserFlow.collect { user ->
                applyMutation {
                    copy(
                        isSignedIn = user != null,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun collectSessionExpired() {
        viewModelScope.launch {
            sessionManager.sessionExpired.collect {
                applyMutation {
                    copy(
                        isSignedIn = false,
                        isLoading = false
                    )
                }
            }
        }
    }
}