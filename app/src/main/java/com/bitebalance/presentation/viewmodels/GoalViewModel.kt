package com.bitebalance.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitebalance.domain.usecase.add.AddNewGoalUseCase
import com.bitebalance.domain.usecase.get.GetGoalsByDateUseCase
import com.bitebalance.domain.usecase.remove.RemoveGoalUseCase
import com.bitebalance.domain.usecase.update.UpdateGoalUseCase
import com.bitebalance.presentation.states.BasicState
import com.ui.model.GoalModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.DateFormat
import java.text.SimpleDateFormat

class GoalViewModel(
    private val removeGoalCase: RemoveGoalUseCase,
    private val addNewGoalUseCase: AddNewGoalUseCase,
    private val updateGoalUseCase: UpdateGoalUseCase,
    private val getGoalsByDateUseCase: GetGoalsByDateUseCase,
) : ViewModel() {
    private val _state = MutableLiveData(BasicState<List<GoalModel>>())
    val state: LiveData<BasicState<List<GoalModel>>> = _state

    fun addNewGoal(
        textValue: String,
        active: Boolean = true,
        achieved: Boolean = false,
    ) {
        addNewGoalUseCase(textValue, active, achieved).onEach {
            _state.value = BasicState(
                data = null,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun getAllGoals(
        monthAndYear: String?,
        dateFormat: SimpleDateFormat,
    ) {
        monthAndYear?.let {
            getGoalsByDateUseCase(monthAndYear, dateFormat).onEach {
                _state.value = BasicState(
                    data = it.data,
                    message = it.message,
                    isLoading = it.isLoading,
                    isSuccessful = it.isSuccessful,
                )
            }.launchIn(viewModelScope)
        }
    }

    fun removeGoal(goalToRemove: GoalModel) {
        removeGoalCase(goalToRemove).onEach {
            _state.value = BasicState(
                data = null,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }

    fun updateGoal(goalModel: GoalModel, checked: Boolean) {
        updateGoalUseCase(goalModel, checked).onEach {
            _state.value = BasicState(
                data = null,
                message = it.message,
                isLoading = it.isLoading,
                isSuccessful = it.isSuccessful,
            )
        }.launchIn(viewModelScope)
    }
}