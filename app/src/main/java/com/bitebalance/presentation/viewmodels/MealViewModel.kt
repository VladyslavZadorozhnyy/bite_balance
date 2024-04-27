package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.domain.usecase.add.AddNewMealUseCase
import com.bitebalance.domain.usecase.get.GetAllMealsUseCase
import com.bitebalance.domain.usecase.remove.RemoveMealUseCase
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.remove.RemoveAllMealsUseCase

class MealViewModel(
    private val getAllMealsUseCase: GetAllMealsUseCase,
    private val removeMealUseCase: RemoveMealUseCase,
    private val removeAllMealsUseCase: RemoveAllMealsUseCase,
    private val addNewDishAndMealUseCase: AddNewDishAndMealUseCase,
    private val addNewMealUseCase: AddNewMealUseCase,
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

    fun removeAllMeals() {
        removeAllMealsUseCase().onEach {
            _state.value = BasicState(
                message = if (it.isSuccessful && it.data != null) it.data else it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun createNewDishAndMeal(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
        eaten: Float,
    ) {
        addNewDishAndMealUseCase(name, prots, fats, carbs, kcals, eaten).onEach {
            _state.value = BasicState(
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun createNewMeal(
        dishName: String,
        eaten: Float
    ) {
        addNewMealUseCase(dishName, eaten).onEach {
            _state.value = BasicState(
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun resetState() {
        _state.value = BasicState()
    }
}