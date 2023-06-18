package com.bitebalance.presentation.states

data class BasicState<T>(
    val data: T? = null,
    val message: String = "",
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = true
)