package com.bitebalance.presentation.viewmodels

import com.ui.model.DishModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.domain.usecase.add.AddNewDishUseCase
import com.bitebalance.domain.usecase.get.GetAllDishesUseCase
import com.bitebalance.domain.usecase.get.GetDishByNameUseCase
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase

class DishViewModel(
    private val addNewDishUseCase: AddNewDishUseCase,
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val removeDishUseCase: RemoveDishUseCase,
    private val getDishByNameUseCase: GetDishByNameUseCase,
): ViewModel() {
    private val _state = MutableLiveData(BasicState<List<DishModel>>())
    val state: LiveData<BasicState<List<DishModel>>> = _state

    fun createDish(name: String, prots: Float, fats: Float, carbs: Float, kcals: Float) {
        addNewDishUseCase(name, prots, fats, carbs, kcals).onEach { result ->
            _state.value = BasicState(
                result.data,
                result.message,
                result.isLoading,
                result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun removeDish(dishName: String) {
        removeDishUseCase(dishName).onEach { result ->
            _state.value = BasicState(
                data = result.data,
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun getAllDishes() {
        getAllDishesUseCase().onEach { result ->
            _state.value = BasicState(
                data = result.data,
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun getDishByName(dishName: String) {
        getDishByNameUseCase(dishName).onEach { result ->
            if (result.data != null) {
                _state.value = BasicState(data = listOf(result.data))
            } else {
                _state.value = BasicState(
                    isSuccessful = false,
                    message = result.message,
                    isLoading = result.isLoading,
                )
            }
        }.launchIn(viewModelScope)
    }

    fun resetState() { _state.value = BasicState() }
}