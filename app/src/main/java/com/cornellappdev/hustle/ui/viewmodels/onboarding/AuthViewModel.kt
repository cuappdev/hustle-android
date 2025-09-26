package com.cornellappdev.hustle.ui.viewmodels.onboarding

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.model.user.AuthResult
import com.cornellappdev.hustle.data.model.user.User
import com.cornellappdev.hustle.data.repository.AuthRepository
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null,
    val isSignedIn: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : HustleViewModel<AuthUiState>(
    initialUiState = AuthUiState()
) {

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        val currentUser = authRepository.getCurrentUser()
        applyMutation {
            copy(
                user = currentUser,
                isSignedIn = currentUser != null
            )
        }
    }

    fun signInWithGoogle() {
        viewModelScope.launch {
            applyMutation { copy(isLoading = true, errorMessage = null) }

            when (val result = authRepository.signInWithGoogle()) {
                is AuthResult.Success -> {
                    applyMutation {
                        copy(
                            isLoading = false,
                            user = result.data,
                            isSignedIn = true,
                            errorMessage = null
                        )
                    }
                }

                is AuthResult.Error -> {
                    applyMutation {
                        copy(
                            isLoading = false,
                            errorMessage = result.exception.message ?: "Sign in failed",
                            isSignedIn = false
                        )
                    }
                }

                is AuthResult.Loading -> {
                    applyMutation { copy(isLoading = true) }
                }
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            applyMutation { copy(isLoading = true) }

            when (val result = authRepository.signOut()) {
                is AuthResult.Success -> {
                    applyMutation {
                        copy(
                            isLoading = false,
                            user = null,
                            isSignedIn = false,
                            errorMessage = null
                        )
                    }
                }

                is AuthResult.Error -> {
                    applyMutation {
                        copy(
                            isLoading = false,
                            errorMessage = result.exception.message ?: "Sign out failed"
                        )
                    }
                }

                is AuthResult.Loading -> {
                    applyMutation { copy(isLoading = true) }
                }
            }
        }
    }

    fun clearError() {
        applyMutation { copy(errorMessage = null) }
    }
}