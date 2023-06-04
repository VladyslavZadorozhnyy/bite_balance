package com.bitebalance.domain.usecase

import com.bitebalance.common.Resource
import com.bitebalance.domain.model.DishModel
import com.bitebalance.domain.model.MealModel
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.components.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class AddNewDishAndMealUseCase(
    private val dishRepository: DishRepository,
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val nutritionValueRepository: NutritionValueRepository
) {

    operator fun invoke(
        name: String,
        prots: Float,
        fats: Float,
        carbs: Float,
        kcals: Float,
        eaten: Float
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        withContext(Dispatchers.IO) {
            val nutritionValueModel = NutritionValueModel(prots, fats, carbs, kcals)
            val nutritionValueModelId: Long = nutritionValueRepository.addNutritionValue(nutritionValueModel)

            val dishIcon = getDishIconRes()
            val dishModel = DishModel(name, dishIcon, nutritionValueModelId)
            val dishModelId = dishRepository.addDish(dishModel)

            val currentTimeId = dateRepository.addDate(dateRepository.getCurrentDate())
            mealRepository.addMeal(MealModel(currentTimeId, dishModelId, eaten))
        }

        emit(Resource.Success("Success"))
    }

    private fun getDishIconRes(): Int {
        val currentHour = dateRepository.getCurrentDate().hour

        return if (currentHour < 12) {
            R.drawable.breakfast_icon
        } else if (currentHour < 19){
            R.drawable.lunch_icon
        } else {
            R.drawable.dinner_icon
        }
    }
}