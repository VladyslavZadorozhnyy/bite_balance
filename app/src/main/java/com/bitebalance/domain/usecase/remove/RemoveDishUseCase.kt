package com.bitebalance.domain.usecase.remove

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
    operator fun invoke(dishName: String): Flow<Resource<List<DishModel>>> = flow {
        var resultMessage = ""
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            try {
                dishRepository.getDishByName(dishName)?.let {
                    nutritionValueRepository.removeNutritionValueById(it.nutritionValId)
                    dishRepository.removeDish(it)
                }
            } catch (exception: Exception) {
                resultMessage = exception.message ?: "Unknown error"
            }
        }

        if (resultMessage.isNotEmpty()) {
            emit(Resource.Error(message = resultMessage))
        } else {
            emit(Resource.Success(message = "Removed successfully"))
        }
    }
}