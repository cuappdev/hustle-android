package com.cornellappdev.hustle.data.model.user

sealed class AuthResult<T> {
    data class Success<T>(val data: T) : AuthResult<T>()
    data class Error<T>(val exception: Exception) : AuthResult<T>()
    class Loading<T> : AuthResult<T>()
}

class InvalidEmailDomainException(message: String) : Exception(message)