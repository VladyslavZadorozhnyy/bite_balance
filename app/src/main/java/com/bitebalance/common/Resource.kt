package com.bitebalance.common

sealed class Resource<T>(
    val data: T? = null,
    val errorMessage: String = "",
    val successMessage: String = "",
    val isLoading: Boolean = false
) {
    class Success<T>(data: T, message: String = "") : Resource<T>(data, successMessage = message)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, errorMessage = message)
    class Loading<T>() : Resource<T>(isLoading = true)
}