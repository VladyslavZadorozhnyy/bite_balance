package com.bitebalance.domain.usecase.add

import java.lang.Exception
import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.*
import com.bitebalance.domain.model.MealModel

class AddNewMealUseCase(
    private val dateRepository: DateRepository,
    private val dishRepository: DishRepository,
    private val mealRepository: MealRepository,
    private val stringRepository: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
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
                    resultMessage = stringRepository.getStr(R.string.meal_created)
                }
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(message = errorMessage))
        else emit(Resource.Success(message = resultMessage))
    }
}