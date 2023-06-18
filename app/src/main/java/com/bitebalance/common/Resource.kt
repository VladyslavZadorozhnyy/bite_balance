package com.bitebalance.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = true
) {
    class Success<T>(message: String = "", data: T? = null) : Resource<T>(data, message, isSuccessful = true)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message, isSuccessful = false)
    class Loading<T> : Resource<T>(isLoading = true)
}