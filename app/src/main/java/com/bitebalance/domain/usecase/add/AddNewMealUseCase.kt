package com.bitebalance.domain.usecase.add

import com.bitebalance.common.Resource
import com.bitebalance.domain.model.MealModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.model.DishModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception

class AddNewMealUseCase(
    private val dateRepository: DateRepository,
    private val dishRepository: DishRepository,
    private val mealRepository: MealRepository,
    private val nutritionValueRepository: NutritionValueRepository
) {
    operator fun invoke(dishName: String, eaten: Float): Flow<Resource<DishModel>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                val currentTimeId = dateRepository.addDate(dateRepository.getCurrentDate())

                dishRepository.getDishByName(dishName)?.let { dishModel ->
                    nutritionValueRepository.getNutritionValueById(dishModel.nutritionValId)?.let {
                        nutritionValueRepository.addNutritionValue(it)
                        mealRepository.addMeal(MealModel(currentTimeId, dishModel.id, eaten))
                    }
                    resultMessage = "Meal created successfully"
                }
            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unknown error"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else {
            emit(Resource.Success(message = resultMessage))
        }
    }
}