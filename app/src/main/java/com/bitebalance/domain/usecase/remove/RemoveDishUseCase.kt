package com.bitebalance.domain.usecase.remove

import android.util.Log
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoveDishUseCase(
    private val dishRepository: DishRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(dishModel: DishModel): Flow<Resource<String>> = flow {
        var resultMessage = ""
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            try {
                nutritionValueRepository.removeNutritionValueById(dishModel.nutritionValId)
                dishRepository.removeDish(dishModel)
            } catch (exception: Exception) {
                resultMessage = exception.message ?: "Unknown error"
            }
        }

        if (resultMessage.isNotEmpty()) {
            emit(Resource.Error(resultMessage))
        } else {
            emit(Resource.Success("Removed successfully"))
        }
    }
}