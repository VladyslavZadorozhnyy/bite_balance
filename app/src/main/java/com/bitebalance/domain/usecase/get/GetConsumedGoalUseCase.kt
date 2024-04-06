package com.bitebalance.domain.usecase.get

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.common.Constants
import com.bitebalance.domain.repository.*

class GetConsumedGoalUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val dishRepository: DishRepository,
    private val stringRepository: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(): Flow<Resource<List<NutritionValueModel>>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var goalConsumption = NutritionValueModel(0F, 0F, 0F, 0F)
        var todayTotalConsumption = NutritionValueModel(0F, 0F, 0F, 0F)

        withContext(Dispatchers.IO) {
            try {
                val currentDate = dateRepository.getCurrentDate()

                val todayMeals = mealRepository.getAllMeals().filter {
                    dateRepository.getDateById(it.mealTimeId)?.day == currentDate.day
                            && dateRepository.getDateById(it.mealTimeId)?.month == currentDate.month
                            && dateRepository.getDateById(it.mealTimeId)?.year == currentDate.year
                }
                val todayDishes = todayMeals.map { dishRepository.getDishById(it.dishId) }

                val todayNutrition = todayDishes.map {it?.let {
                        dishModel -> nutritionValueRepository.getNutritionValueById(dishModel.nutritionValId) }
                }

                for (i in todayNutrition.indices) { todayNutrition[i]?.let {
                    todayTotalConsumption = NutritionValueModel(
                        prots = todayTotalConsumption.prots + it.prots * todayMeals[i].amount,
                        fats = todayTotalConsumption.fats + it.fats * todayMeals[i].amount,
                        carbs = todayTotalConsumption.carbs + it.carbs * todayMeals[i].amount,
                        kcals = todayTotalConsumption.kcals + it.kcals * todayMeals[i].amount,
                        )
                    }
                }
                goalConsumption = if (nutritionValueRepository.getGoalConsumption() == null) {
                    nutritionValueRepository.setGoalConsumption(Constants.DEFAULT_GOAL_CONSUMPTION)
                    nutritionValueRepository.getGoalConsumption()!!
                } else {
                    nutritionValueRepository.getGoalConsumption()!!
                }
            } catch (exception: Exception) {
                resultMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
            }
        }

        if (resultMessage.isNotEmpty()) emit(Resource.Error(message = resultMessage))
        else emit(Resource.Success(data = listOf(goalConsumption, todayTotalConsumption)))
    }
}