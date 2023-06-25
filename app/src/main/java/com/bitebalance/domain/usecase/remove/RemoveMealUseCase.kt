package com.bitebalance.domain.usecase.remove

import android.util.Log
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.MealRepository
import com.ui.model.MealModelUnboxed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoveMealUseCase(
    private val mealRepository: MealRepository,
    private val dateRepository: DateRepository,
) {
    operator fun invoke(mealModelUnboxed: MealModelUnboxed): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                mealRepository.removeMealById(mealModelUnboxed.id)
                dateRepository.removeById(mealModelUnboxed.mealTimeId)
                resultMessage = "Removed successfully"
            } catch (exception: Exception) {
                Log.d("AAADIP", "exception: $exception")
                errorMessage = exception.message ?: "Unknown message"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(errorMessage))
        } else {
            emit(Resource.Success(data = resultMessage))
        }
    }
}