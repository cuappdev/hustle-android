package com.cornellappdev.hustle.data.model

sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error<out T>(val exception: Exception) : ResponseState<T>()
    data object Loading : ResponseState<Nothing>()
}