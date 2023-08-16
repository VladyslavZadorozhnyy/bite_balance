package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.DishRepository
import com.bitebalance.domain.repository.MealRepository
import com.bitebalance.domain.repository.NutritionValueRepository
import com.ui.model.NutritionValueModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GetNutritionValuesByDateUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val dishRepository: DishRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(
        month: Int?,
        year: Int?,
        getNextDate: Boolean,
    ): Flow<Resource<Pair<List<NutritionValueModel>, NutritionValueModel>>> = flow {
        emit(Resource.Loading())

        //val result: List<NutritionValueModel>
        val dayConsumptionMap: MutableMap<Int, NutritionValueModel> = mutableMapOf()
        var goalConsumption: NutritionValueModel? = null
        var errorMessage = ""

        withContext(Dispatchers.IO) {
            try {
                val neededMonth = if (month == null || year == null) {
                    dateRepository.getCurrentDate().month
                } else if (getNextDate) {
                    if (month < 11) (month + 1) else 0
                } else {
                    if (month > 0) (month - 1) else 11
                }

                var neededYear = if (month == null || year == null) dateRepository.getCurrentDate().year else year
                neededYear = when (neededMonth) {
                    0 -> neededYear + 1
                    11 -> neededYear - 1
                    else -> neededYear
                }

                val daysCount = dateRepository.getDaysCountInMonth(neededMonth, neededYear)
                for (i in 0 until daysCount) {
                    dayConsumptionMap[i] = NutritionValueModel(0F, 0F, 0F, 0F)
                }

                mealRepository.getAllMeals()
                    .filter { dateRepository.getDateById(it.mealTimeId)?.month == neededMonth &&
                            dateRepository.getDateById(it.mealTimeId)?.year == neededYear }
                    .groupBy {
                        dateRepository.getDateById(it.mealTimeId)?.day
                    }.asIterable().forEach { mapEntry ->
                        var summedNutritionValue = NutritionValueModel(0F, 0F, 0F, 0F)

                        mapEntry.value.map { mealModel ->
                            dishRepository.getDishById(mealModel.dishId)
                        }.map { dishModel ->
                            nutritionValueRepository.getNutritionValueById(dishModel?.nutritionValId ?: -1)
                        }.forEach { nutritionModel ->
                            nutritionModel?.let { summedNutritionValue += nutritionModel }
                        }
                        mapEntry.key?.let { dayConsumptionMap[it] = summedNutritionValue }
                    }

                nutritionValueRepository.getGoalConsumption()?.let { goalConsumption = it }
            } catch (exception: Throwable) {
                errorMessage = exception.message ?: "Unknown error"
            }
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else if (goalConsumption != null) {
            emit(Resource.Success(
                data = Pair(dayConsumptionMap.values.toList(), goalConsumption!!)
            ))
        } else {
            emit(Resource.Error(message = "Goal consumption not downloaded"))
        }
    }
}