package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.get.GetGreetingsUseCase
import com.bitebalance.domain.usecase.get.GetMonthUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat

class DateViewModel(
    private val getGreetingsUseCase: GetGreetingsUseCase,
    private val getMonthUseCase: GetMonthUseCase
) : ViewModel() {
    private val _state = MutableLiveData(String())
    val state: LiveData<String> = _state

    fun getGreetingsValue() {
        getGreetingsUseCase().onEach {
            _state.value = it.data ?: "Unknown error happened"
        }.launchIn(viewModelScope)
    }

    fun getCurrentMonth(dateFormat: SimpleDateFormat) {
        getMonthUseCase(
            dateFormat = dateFormat
        ).onEach {
            _state.value = it.data ?: "Unknown error happened"
        }.launchIn(viewModelScope)
    }

    fun getPrevMonth(dateFormat: SimpleDateFormat, currentMonth: String) {
        getMonthUseCase(
            dateFormat = dateFormat,
            currentMonth = currentMonth,
            getPrevMonth = true,
            getNextMonth = false
        ).onEach {
            _state.value = it.data ?: "Unknown error happened"
        }.launchIn(viewModelScope)
    }

    fun getNextMonth(dateFormat: SimpleDateFormat, currentMonth: String) {
        getMonthUseCase(
            dateFormat = dateFormat,
            currentMonth = currentMonth,
            getPrevMonth = false,
            getNextMonth = true
        ).onEach {
            _state.value = it.data ?: "Unknown error happened"
        }.launchIn(viewModelScope)
    }
}