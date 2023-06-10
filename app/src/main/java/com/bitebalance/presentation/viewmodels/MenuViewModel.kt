package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.GetAllDishesUseCase
import com.bitebalance.presentation.states.MenuState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MenuViewModel(
    private val getAllDishesUseCase: GetAllDishesUseCase,
) : ViewModel() {
    private val _state = MutableLiveData(MenuState())
    val state: LiveData<MenuState> = _state

    fun getAllDishes() {
        getAllDishesUseCase().onEach { result ->
            _state.value = MenuState(
                dishes = result.data,
                errorMessage = result.errorMessage
            )
        }.launchIn(viewModelScope)
    }
}