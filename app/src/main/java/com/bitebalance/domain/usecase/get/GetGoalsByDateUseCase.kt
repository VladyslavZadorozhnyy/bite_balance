package com.bitebalance.domain.usecase.get

import com.ui.model.GoalModel
import java.text.SimpleDateFormat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.GoalRepository

class GetGoalsByDateUseCase(
    private val goalRepository: GoalRepository,
    private val dateRepository: DateRepository,
) {
    operator fun invoke(date: String, dateFormat: SimpleDateFormat): Flow<Resource<List<GoalModel>>> = flow {
        emit(Resource.Loading())

        val result = withContext(Dispatchers.IO) {
            val result: ArrayList<GoalModel> = ArrayList()
            val currentDate = dateRepository.getCurrentDate()
            val requestedDate = dateRepository.getDateFromString(dateFormat, date)

            goalRepository.getAllGoals().forEach { goal ->
                dateRepository.getDateById(goal.dateCreatedId)?.let { goalDate ->
                    val goalDateIsValid = goalDate.month == requestedDate.month && goalDate.year == requestedDate.year
                    val goalDateIsCurrent = currentDate.month == goalDate.month && currentDate.year == goalDate.year

                    if (goalDateIsValid && goalDateIsCurrent) {
                        result.add(goal)
                    } else if (goalDateIsValid) {
                        val nonActiveModel = GoalModel(
                            textValue = goal.textValue,
                            active = false,
                            achieved = goal.achieved,
                            dateCreatedId = goal.dateCreatedId,
                            id = goal.id,
                        )
                        goalRepository.updateGoal(nonActiveModel)
                        result.add(nonActiveModel)
                    }
                }
            }
            return@withContext result
        }
        emit(Resource.Success(data = result))
    }
}