package com.bitebalance.domain.usecase.add

import com.ui.components.R
import com.ui.model.DishModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.bitebalance.common.Constants
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.*
import com.bitebalance.domain.model.MealModel

class AddNewDishAndMealUseCase(
    private val dishRepository: DishRepository,
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val stringRepository: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {

    operator fun invoke(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
        eaten: Float,
    ): Flow<Resource<List<DishModel>>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                val nutritionValueModel = NutritionValueModel(prots, fats, carbs, kcals)
                val nutritionValueModelId: Long = nutritionValueRepository.addNutritionValue(nutritionValueModel)

                val dishIcon = getDishIconRes()
                val dishModel = DishModel(name, dishIcon, nutritionValueModelId)
                val dishModelId = dishRepository.addDish(dishModel)

                val currentTimeId = dateRepository.addDate(dateRepository.getCurrentDate())
                mealRepository.addMeal(MealModel(currentTimeId, dishModelId, eaten))

                resultMessage = stringRepository.getStr(R.string.meal_dish_created)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }
        if (errorMessage.isNotEmpty())
            emit(Resource.Error(message = errorMessage))
        else
            emit(Resource.Success(message = resultMessage))
    }

    private fun getDishIconRes(): Int {
        val currentHour = dateRepository.getCurrentDate().hour

        return if (currentHour < Constants.BREAKFAST_HOUR) {
            R.drawable.breakfast_icon
        } else if (currentHour < Constants.LUNCH_HOUR) {
            R.drawable.lunch_icon
        } else {
            R.drawable.dinner_icon
        }
    }
}