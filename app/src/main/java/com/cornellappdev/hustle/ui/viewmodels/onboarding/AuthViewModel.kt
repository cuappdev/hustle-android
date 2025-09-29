package com.cornellappdev.hustle.ui.viewmodels.onboarding

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.model.user.User
import com.cornellappdev.hustle.data.repository.AuthRepository
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthUiState(
    val user: User? = null,
    val isSignedIn: Boolean = false,
    val actionState: ActionState = ActionState.Idle,
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : HustleViewModel<AuthUiState>(
    initialUiState = AuthUiState()
) {

    init {
        viewModelScope.launch {
            authRepository.currentUserFlow.collect { user ->
                applyMutation {
                    copy(
                        user = user,
                        isSignedIn = user != null
                    )
                }
            }
        }
    }

    fun signInWithGoogle() {
        executeAuthAction {
            authRepository.signInWithGoogle()
        }
    }

    fun signOut() {
        executeAuthAction {
            authRepository.signOut()
        }
    }

    fun clearActionState() {
        applyMutation { copy(actionState = ActionState.Idle) }
    }

    private fun executeAuthAction(
        authAction: suspend () -> Result<*>
    ) {
        viewModelScope.launch {
            applyMutation { copy(actionState = ActionState.Loading) }
            authAction()
                .onSuccess {
                    applyMutation { copy(actionState = ActionState.Success) }
                }
                .onFailure { exception ->
                    applyMutation {
                        copy(
                            actionState = ActionState.Error(
                                exception.message ?: "Authentication failed"
                            )
                        )
                    }
                }
        }
    }
}