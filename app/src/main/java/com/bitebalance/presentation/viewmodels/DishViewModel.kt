package com.bitebalance.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.add.AddNewDishAndMealUseCase
import com.bitebalance.domain.usecase.get.GetAllDishesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

// AAADIP remove later TODO
class DishViewModel(
    private val addNewDishAndMealUseCase: AddNewDishAndMealUseCase,
    private val getAllDishesUseCase: GetAllDishesUseCase,
): ViewModel() {

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
}