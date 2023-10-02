package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.get.GetNutritionValuesByDateUseCase
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.presentation.states.StatsState
import com.ui.model.NutritionValueModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat

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
                month = 1,
                year = 1,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }
}