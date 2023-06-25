package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.get.GetAllMealsUseCase
import com.bitebalance.domain.usecase.remove.RemoveMealUseCase
import com.bitebalance.presentation.states.BasicState
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MealViewModel(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val removeMealUseCase: RemoveMealUseCase
): ViewModel() {
    private val _state = MutableLiveData(BasicState<List<MealModelUnboxed>>())
    val state: LiveData<BasicState<List<MealModelUnboxed>>> = _state

    fun getMeals() {
        getAllMealsUseCase().onEach {
            _state.value = BasicState(
                data = it.data,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun removeMeal(meal: MealModelUnboxed) {
        removeMealUseCase(meal).onEach {
            _state.value = BasicState(
                message = if (it.isSuccessful && it.data != null) it.data else it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }
}