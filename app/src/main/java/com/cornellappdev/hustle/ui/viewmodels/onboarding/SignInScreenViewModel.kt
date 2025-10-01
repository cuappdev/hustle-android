package com.cornellappdev.hustle.ui.viewmodels.onboarding

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.repository.AuthRepository
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import com.cornellappdev.hustle.util.viewmodels.executeActionStatefully
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignInScreenUiState(
    val isSignedIn: Boolean = false,
    val actionState: ActionState = ActionState.Idle,
)

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : HustleViewModel<SignInScreenUiState>(
    initialUiState = SignInScreenUiState()
) {

    init {
        viewModelScope.launch {
            authRepository.currentUserFlow.collect { user ->
                applyMutation { copy(isSignedIn = user != null) }
            }
        }
    }

    fun signInWithGoogle() {
        executeActionStatefully(
            action = { authRepository.signInWithGoogle() },
            updateActionState = { copy(actionState = it) })
    }

    fun clearActionState() {
        applyMutation { copy(actionState = ActionState.Idle) }
    }
}