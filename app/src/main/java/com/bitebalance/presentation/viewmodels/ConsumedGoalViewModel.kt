package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.bitebalance.common.Resource
import com.bitebalance.domain.usecase.GetEatenTodayUseCase
import kotlinx.coroutines.flow.onEach

class ConsumptionViewModel(
    private val getEatenTodayUseCase: GetEatenTodayUseCase,
): ViewModel() {
    private var eatenTodayActual = false

    fun getConsumedGoalValues(navigationViewModel: NavigationViewModel) {
        if (!eatenTodayActual) {
            getEatenTodayUseCase().onEach { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                    is Resource.Success -> {

                        eatenTodayActual = true
                    }
                }
            }
        }
    }
}