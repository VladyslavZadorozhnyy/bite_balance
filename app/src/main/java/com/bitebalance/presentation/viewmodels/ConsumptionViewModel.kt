package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.GetEatenTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConsumptionViewModel(
    private val getEatenTodayUseCase: GetEatenTodayUseCase,
): ViewModel() {
    fun getEatenToday() {
        viewModelScope.launch(Dispatchers.IO) { getEatenTodayUseCase() }
    }
}