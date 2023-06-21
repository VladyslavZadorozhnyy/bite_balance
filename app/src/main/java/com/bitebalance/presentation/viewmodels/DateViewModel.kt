package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.get.GetGreetingsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DateViewModel(
    private val getGreetingsUseCase: GetGreetingsUseCase
) : ViewModel() {
    private val _state = MutableLiveData(String())
    val state: LiveData<String> = _state

    fun getGreetingsValue() {
        getGreetingsUseCase().onEach {
            _state.value = it.data ?: "Unknown error happened"
        }.launchIn(viewModelScope)
    }
}