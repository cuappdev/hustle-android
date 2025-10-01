package com.cornellappdev.hustle.util.auth

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import kotlinx.coroutines.launch

fun <UiState> HustleViewModel<UiState>.executeAuthAction(
    authAction: suspend () -> Result<*>,
    updateActionState: UiState.(ActionState) -> UiState
) {
    viewModelScope.launch {
        applyMutation { updateActionState(ActionState.Loading) }
        authAction()
            .onSuccess {
                applyMutation { updateActionState(ActionState.Success) }
            }
            .onFailure { exception ->
                applyMutation {
                    updateActionState(
                        ActionState.Error(
                            exception.message ?: "Authentication failed"
                        )
                    )
                }
            }
    }
}