package com.bitebalance.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.get.GetAllDishesUseCase
import com.bitebalance.domain.usecase.remove.RemoveDishUseCase
import com.bitebalance.presentation.states.BasicState
import com.ui.model.DishModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DishViewModel(
    private val addNewDishAndMealUseCase: AddNewDishAndMealUseCase,
    private val getAllDishesUseCase: GetAllDishesUseCase,
    private val removeDishUseCase: RemoveDishUseCase,
): ViewModel() {
    private val _state = MutableLiveData(BasicState<String>())
    val state: LiveData<BasicState<String>> = _state

    fun createDish(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
        eaten: Float
    ) {
        addNewDishAndMealUseCase(name, prots, fats, carbs, kcals, eaten).onEach { result ->
            Log.d("AAADIP", "Result from addNewDishAndMealUseCase is: $result")
        }.launchIn(viewModelScope)
    }

    fun removeDish(dishModel: DishModel) {
        removeDishUseCase(dishModel).onEach { result ->
            _state.value = BasicState<String>(
                isSuccess = result.errorMessage.isEmpty(),
                isLoading = result.isLoading,
                data = result.errorMessage.ifEmpty { result.data }
            )
        }.launchIn(viewModelScope)
    }
}