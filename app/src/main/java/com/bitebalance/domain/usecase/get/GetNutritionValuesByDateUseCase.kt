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
import java.text.SimpleDateFormat

class GetNutritionValuesByDateUseCase(
    private val dateRepository: DateRepository,
    private val mealRepository: MealRepository,
    private val dishRepository: DishRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    private val emptyNutritionValue = NutritionValueModel(0F, 0F, 0F, 0F)
    operator fun invoke(
        dateFormat: SimpleDateFormat,
        dateValue: String,
    ): Flow<Resource<Pair<List<NutritionValueModel>, NutritionValueModel>>> = flow {
        emit(Resource.Loading())

        val dayConsumptionMap: MutableMap<Int, NutritionValueModel> = mutableMapOf()
        var goalConsumption: NutritionValueModel? = null
        var errorMessage = ""

        withContext(Dispatchers.IO) {
            try {
                val dateModel = dateRepository.getDateFromString(dateFormat, dateValue)
                val daysCount = dateRepository.getDaysCountInMonth(dateModel.month, dateModel.year)

                for (i in 0 until daysCount) {
                    dayConsumptionMap[i] = emptyNutritionValue
                }

                mealRepository.getAllMeals()
                    .filter { dateRepository.getDateById(it.mealTimeId)?.month == dateModel.month &&
                            dateRepository.getDateById(it.mealTimeId)?.year == dateModel.year }
                    .groupBy {
                        dateRepository.getDateById(it.mealTimeId)?.day
                    }.asIterable().forEach { mapEntry ->
                        var summedNutritionValue = emptyNutritionValue

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