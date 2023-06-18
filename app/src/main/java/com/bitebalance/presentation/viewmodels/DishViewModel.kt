package com.bitebalance.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.add.AddNewDishUseCase
import com.bitebalance.domain.usecase.get.GetAllDishesUseCase
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase
import com.bitebalance.presentation.states.BasicState
import com.ui.model.DishModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DishViewModel(
    private val addNewDishAndMealUseCase: AddNewDishAndMealUseCase,
    private val addNewDishUseCase: AddNewDishUseCase,
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val removeDishUseCase: RemoveDishUseCase,
): ViewModel() {
    private val _state = MutableLiveData(BasicState<List<DishModel>>())
    val state: LiveData<BasicState<List<DishModel>>> = _state

    fun createDish(name: String, prots: Float, fats: Float, carbs: Float, kcals: Float) {
        addNewDishUseCase.invoke(name, prots, fats, carbs, kcals).onEach { result ->
            _state.value = BasicState(
                result.data,
                result.message,
                result.isLoading,
                result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun removeDish(dishModel: DishModel) {
        removeDishUseCase(dishModel).onEach { result ->
            _state.value = BasicState(
                data = result.data,
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun removeDishByName(dishName: String) {

    }

    fun resetState() { _state.value = BasicState() }
}