package com.bitebalance.domain.usecase.get

import android.util.Log
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.GoalRepository
import com.ui.model.GoalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

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
//                    if (goalDate.month == requestedDate.month && goalDate.year == requestedDate.year) {
//                        result.add(goal)
//                        if (currentDate.month != goalDate.month || currentDate.year != goalDate.year) {
//
//                        }
//                    }
                }
            }
//            Log.d("AAADIP", "dateRepository.getDateFromString(dateFormat, date): ${dateRepository.getDateFromString(dateFormat, date)}")
//            Log.d("AAADIP", "goalRepository.getAllGoals().get(0).dateCreatedId: ${goalRepository.getAllGoals().get(0).dateCreatedId}")
//            Log.d("AAADIP", "dateRepository.getDateById(goalRepository.getAllGoals().get(0).dateCreatedId): ${dateRepository.getDateById(goalRepository.getAllGoals().get(0).dateCreatedId)}")
//
//            Log.d("AAADIP", "currentDate: $currentDate")
//            Log.d("AAADIP", "requestedDate: $requestedDate")

            return@withContext result
        }
        emit(Resource.Success(data = result))
    }
}