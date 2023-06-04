package com.bitebalance.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.AddNewDishAndMealUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DishViewModel(
    private val addNewDishAndMealUseCase: AddNewDishAndMealUseCase,
): ViewModel() {

    fun createDish(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
        eaten: Float
    ) {
        addNewDishAndMealUseCase.invoke(name, prots, fats, carbs, kcals, eaten).onEach { result ->
            Log.d("AAADIP", "Result from addNewDishAndMealUseCase is: $result")
        }.launchIn(viewModelScope)
    }
}