package com.cornellappdev.hustle.ui.viewmodels.profile

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.model.user.User
import com.cornellappdev.hustle.data.repository.AuthRepository
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import com.cornellappdev.hustle.util.auth.executeAuthAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileScreenUiState(
    val user: User? = null,
    val authActionState: ActionState = ActionState.Idle,
)

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : HustleViewModel<ProfileScreenUiState>(
    initialUiState = ProfileScreenUiState()
) {

    init {
        viewModelScope.launch {
            authRepository.currentUserFlow.collect { user ->
                applyMutation { copy(user = user) }
            }
        }
    }

    fun signOut() {
        executeAuthAction(
            authAction = { authRepository.signOut() },
            updateActionState = { copy(authActionState = it) })
    }
}