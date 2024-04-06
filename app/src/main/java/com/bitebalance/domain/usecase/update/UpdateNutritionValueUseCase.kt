package com.bitebalance.domain.usecase.update

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.StringRepository
import com.bitebalance.domain.repository.NutritionValueRepository


class UpdateNutritionValueUseCase(
    private val nutritionValueRepository: NutritionValueRepository,
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(dishName: String, inputValues: List<String>): Flow<Resource<NutritionValueModel?>> = flow {
        try {
            emit(Resource.Loading())

            if (inputValues.any { it.isNotEmpty() && it.toFloat() < 0.0 })
                throw Exception(stringRepository.getStr(R.string.negative_val_error))

            val nutritionValueData: NutritionValueModel?
            val nutritionValueId: Long?
            withContext(Dispatchers.IO) {
                nutritionValueId = dishRepository.getDishByName(dishName)?.nutritionValId ?: -1

                nutritionValueRepository.updateNutritionValueById(
                    id = nutritionValueId,
                    nutritionValueModel = NutritionValueModel(
                        prots = if (inputValues[0].isNotEmpty()) inputValues[0].toFloat() else 0.0f,
                        fats = if (inputValues[1].isNotEmpty()) inputValues[1].toFloat() else 0.0f,
                        carbs = if (inputValues[2].isNotEmpty()) inputValues[2].toFloat() else 0.0f,
                        kcals = if (inputValues[3].isNotEmpty()) inputValues[3].toFloat() else 0.0f,
                    )
                )
                nutritionValueData = nutritionValueRepository.getNutritionValueById(nutritionValueId)
            }
            emit(Resource.Success(stringRepository.getStr(R.string.dish_update_success), nutritionValueData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: stringRepository.getStr(R.string.unknown_error)))
        }
    }
}