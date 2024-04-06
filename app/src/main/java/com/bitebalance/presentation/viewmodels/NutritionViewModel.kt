package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import com.ui.model.NutritionValueModel
import kotlinx.coroutines.flow.launchIn
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.domain.usecase.get.GetConsumedGoalUseCase
import com.bitebalance.domain.usecase.get.GetNutritionValueUseCase
import com.bitebalance.domain.usecase.update.UpdateNutritionValueUseCase
import com.bitebalance.domain.usecase.update.UpdateConsumptionGoalUseCase


class NutritionViewModel(
    private val getConsumedGoalUseCase: GetConsumedGoalUseCase,
    private val getNutritionValueUseCase: GetNutritionValueUseCase,
    private val updateNutritionValueUseCase: UpdateNutritionValueUseCase,
    private val updateConsumptionGoalUseCase: UpdateConsumptionGoalUseCase,
) : ViewModel() {
    private val _state = MutableLiveData(BasicState<List<NutritionValueModel>>())
    val state: LiveData<BasicState<List<NutritionValueModel>>> = _state

    fun getNutritionValue(id: Long) {
        getNutritionValueUseCase(id).onEach { result ->
            _state.value = BasicState(
                data = if (result.data == null) { null } else { listOf(result.data) },
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun updateNutritionValue(dishName: String, inputValues: List<String>) {
        updateNutritionValueUseCase(dishName, inputValues).onEach { result ->
            _state.value = BasicState(
                data = if (result.data == null) { null } else { listOf(result.data) },
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun getConsumedGoalValues() {
        getConsumedGoalUseCase().onEach { result ->
            _state.value = BasicState(
                data = result.data,
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun updateConsumedGoalValue(component: String, newValue: String) {
        updateConsumptionGoalUseCase(
            component,
            newValue,
        ).onEach { result ->
            _state.value = BasicState(
                data = null,
                message = result.message,
                isLoading = result.isLoading,
                isSuccessful = result.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun resetState() { _state.value = BasicState() }
}