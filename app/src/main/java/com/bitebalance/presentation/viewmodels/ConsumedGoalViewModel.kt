package com.bitebalance.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.common.Resource
import com.bitebalance.domain.usecase.GetConsumedGoalUseCase
import com.bitebalance.presentation.states.NavigationState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ConsumedGoalViewModel(
    private val getConsumedGoalUseCase: GetConsumedGoalUseCase,
): ViewModel() {
    private val _state = MutableLiveData(NavigationState())
    val state: LiveData<NavigationState> = _state

    fun getConsumedGoalValues() {
        getConsumedGoalUseCase().onEach { result ->
            Log.d("AAADIP", "result: $result")

            when (result) {
                is Resource.Loading -> {}
                is Resource.Error -> {}
                is Resource.Success -> {}
            }
        }.launchIn(viewModelScope)
    }
}