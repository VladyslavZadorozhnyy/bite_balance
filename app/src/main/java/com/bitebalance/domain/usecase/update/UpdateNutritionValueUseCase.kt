package com.bitebalance.domain.usecase.update

import com.bitebalance.common.Resource
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.NutritionValueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class UpdateNutritionValueUseCase(
    private val nutritionValueRepository: NutritionValueRepository
) {
    operator fun invoke(id: Long, inputValues: List<String>): Flow<Resource<NutritionValueModel?>> = flow {
        try {
            emit(Resource.Loading())

            if (inputValues.any { it.isNotEmpty() && it.toFloat() < 0.0 }) {
                throw Exception("Negative values are not allowed")
            }

            val nutritionValueData: NutritionValueModel?

            withContext(Dispatchers.IO) {
                nutritionValueRepository.updateNutritionValueById(
                    id = id,
                    nutritionValueModel = NutritionValueModel(
                        prots = if (inputValues[0].isNotEmpty()) inputValues[0].toFloat() else 0.0f,
                        fats = if (inputValues[1].isNotEmpty()) inputValues[1].toFloat() else 0.0f,
                        carbs = if (inputValues[2].isNotEmpty()) inputValues[2].toFloat() else 0.0f,
                        kcals = if (inputValues[3].isNotEmpty()) inputValues[3].toFloat() else 0.0f,
                    )
                )

                nutritionValueData = nutritionValueRepository.getNutritionValueById(id)
            }

            emit(Resource.Success(nutritionValueData, "Dish updated successfully"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error occurred: ${e.message}"))
        }
    }
}