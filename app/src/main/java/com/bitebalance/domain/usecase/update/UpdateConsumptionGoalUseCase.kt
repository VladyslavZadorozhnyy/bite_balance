package com.bitebalance.domain.usecase.update

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.bitebalance.common.Resource
import com.ui.model.NutritionValueModel
import com.bitebalance.domain.repository.*
import com.bitebalance.common.Constants.COMPONENT_VAL_MAX
import com.bitebalance.common.Constants.COMPONENT_VAL_MIN
import com.bitebalance.common.Constants.DEFAULT_GOAL_CONSUMPTION

class UpdateConsumptionGoalUseCase(
    private val strRepo: StringRepository,
    private val nutritionValueRepository: NutritionValueRepository,
) {
    operator fun invoke(
        component: String,
        value: String,
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var resultMessage = ""
        var errorMessage = ""
        withContext(Dispatchers.IO) {
            try {
                val newValue = value.toFloat()
                if (newValue <= COMPONENT_VAL_MIN || newValue >= COMPONENT_VAL_MAX) {
                    throw java.lang.Exception(strRepo.getStr(
                        R.string.value_out_of_bounds,
                        COMPONENT_VAL_MIN.toString(),
                        COMPONENT_VAL_MAX.toString(),
                    ))
                }

                val goal = if (nutritionValueRepository.getGoalConsumption() == null)
                    DEFAULT_GOAL_CONSUMPTION
                else
                    nutritionValueRepository.getGoalConsumption()!!

                val updatedGoalConsumption = NutritionValueModel(
                    prots = if (component == strRepo.getStr(R.string.prots_g))
                        newValue else goal.prots,
                    fats = if (component == strRepo.getStr(R.string.fats_g))
                        newValue else goal.fats,
                    carbs = if (component == strRepo.getStr(R.string.carbs_g))
                        newValue else goal.carbs,
                    kcals = if (component == strRepo.getStr(R.string.kcals_g))
                        newValue else goal.kcals,
                )
                nutritionValueRepository.setGoalConsumption(updatedGoalConsumption)
                resultMessage = strRepo.getStr(R.string.component_update_success)
            } catch (exception: NumberFormatException) {
                errorMessage = strRepo.getStr(R.string.num_format_exception)
            } catch (exception: Exception) {
                errorMessage = exception.message ?: strRepo.getStr(R.string.unknown_error)
            }
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(message = errorMessage))
        else emit(Resource.Success(message = resultMessage))
    }
}