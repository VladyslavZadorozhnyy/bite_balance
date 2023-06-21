package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetConsumedGoalUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val dishRepository: DishRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    private val defaultGoalConsumption = NutritionValueModel(
        prots = 51F,
        fats = 62F,
        carbs = 275F,
        kcals = 2250F,
    )

    operator fun invoke(): Flow<Resource<List<NutritionValueModel>>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var goalConsumption = NutritionValueModel(0F, 0F, 0F, 0F)
        var todayTotalConsumption = NutritionValueModel(0F, 0F, 0F, 0F)

        withContext(Dispatchers.IO) {
            try {
                val todayMeals = mealRepository.getAllMeals().filter { it.mealTimeId == dateRepository.getCurrentDateId() }
                val todayDishes = todayMeals.map { dishRepository.getDishById(it.dishId) }

                val todayNutrition = todayDishes.map {it?.let {
                        dishModel -> nutritionValueRepository.getNutritionValueById(dishModel.nutritionValId) }
                }

                for (i in todayNutrition.indices) { todayNutrition[i]?.let {
                    todayTotalConsumption = NutritionValueModel(
                        prots = todayTotalConsumption.prots + it.prots * todayMeals[i].amount,
                        fats = todayTotalConsumption.fats + it.fats * todayMeals[i].amount,
                        carbs = todayTotalConsumption.carbs + it.carbs * todayMeals[i].amount,
                        kcals = todayTotalConsumption.kcals + it.kcals * todayMeals[i].amount
                        )
                    }
                }

                goalConsumption = if (nutritionValueRepository.getGoalConsumption() == null) {
                    nutritionValueRepository.setGoalConsumption(defaultGoalConsumption)
                    nutritionValueRepository.getGoalConsumption()!!
                } else {
                    nutritionValueRepository.getGoalConsumption()!!
                }

            } catch (exception: Exception) {
                resultMessage = exception.message ?: "Unknown error"
            }
        }

        if (resultMessage.isNotEmpty()) {
            emit(Resource.Error(message = resultMessage))
        } else {
            emit(Resource.Success(data = listOf(goalConsumption, todayTotalConsumption)))
        }
    }
}