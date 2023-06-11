package com.bitebalance.presentation.states

data class BasicState<T>(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = false,
    val data: T? = null
)