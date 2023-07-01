package com.bitebalance.domain.usecase.remove

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoveAllMealsUseCase(
    private val mealRepository: MealRepository,
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                mealRepository.removeAllMeals()
                resultMessage = "Reset is successful"
            } catch (exception: Exception) {
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