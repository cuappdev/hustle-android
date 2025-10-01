package com.cornellappdev.hustle.util.viewmodels

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.ui.viewmodels.ActionState
import com.cornellappdev.hustle.ui.viewmodels.HustleViewModel
import kotlinx.coroutines.launch

fun <UiState> HustleViewModel<UiState>.executeActionStatefully(
    action: suspend () -> Result<*>,
    updateActionState: UiState.(ActionState) -> UiState
) {
    viewModelScope.launch {
        applyMutation { updateActionState(ActionState.Loading) }
        action()
            .onSuccess {
                applyMutation { updateActionState(ActionState.Success) }
            }
            .onFailure { exception ->
                applyMutation {
                    updateActionState(
                        ActionState.Error(
                            exception.message ?: "An unknown error occurred"
                        )
                    )
                }
            }
    }
}