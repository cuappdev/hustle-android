package com.cornellappdev.hustle.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.cornellappdev.hustle.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RootUiState(
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = true,
)

@HiltViewModel
class RootViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : HustleViewModel<RootUiState>(
    initialUiState = RootUiState()
) {
    init {
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
}