package com.cornellappdev.hustle.ui.viewmodels
sealed class ActionState {
    object Loading : ActionState()
    data class Error(val message: String) : ActionState()
    object Success : ActionState()
    object Idle : ActionState()
}