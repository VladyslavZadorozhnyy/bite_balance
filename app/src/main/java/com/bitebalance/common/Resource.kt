package com.bitebalance.common

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, errorMessage = message)
    class Loading<T>(data: T? = null) : Resource<T>(isLoading = true)
}