package com.bitebalance.presentation.viewmodels

import java.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import com.ui.model.NutritionValueModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import com.bitebalance.presentation.states.StatsState
import com.bitebalance.domain.usecase.get.GetNutritionValuesByDateUseCase

class StatsViewModel(
    private val getNutritionValuesByDateUseCase: GetNutritionValuesByDateUseCase
) : ViewModel() {
    private val _state = MutableLiveData(StatsState())
    val state: LiveData<StatsState> = _state

    val emptyNutritionValue = NutritionValueModel(0F, 0F, 0F, 0F)

    fun getStatsByMonthAndYear(
        dateFormat: SimpleDateFormat,
        dateValue: String,
    ) {
        getNutritionValuesByDateUseCase(dateFormat, dateValue).onEach {
            _state.value = StatsState(
                monthNutrition = it.data?.first,
                goalConsumption = it.data?.second,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }
}