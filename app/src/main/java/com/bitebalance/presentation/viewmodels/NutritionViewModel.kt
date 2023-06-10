package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.GetNutritionValueUseCase
import com.bitebalance.presentation.states.NutritionValueState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class NutritionViewModel(
    private val getNutritionValueUseCase: GetNutritionValueUseCase,
) : ViewModel() {
    private val _state = MutableLiveData(NutritionValueState())
    val state: LiveData<NutritionValueState> = _state

    fun getNutritionValue(id: Long) {
        getNutritionValueUseCase(id).onEach { result ->
            _state.value = NutritionValueState(result.data)
        }.launchIn(viewModelScope)
    }
}