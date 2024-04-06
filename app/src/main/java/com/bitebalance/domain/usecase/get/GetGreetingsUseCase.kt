package com.bitebalance.domain.usecase.get

import com.ui.components.R
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import com.bitebalance.common.Resource
import com.bitebalance.common.Constants
import com.bitebalance.domain.repository.DateRepository
import com.bitebalance.domain.repository.StringRepository

class GetGreetingsUseCase(
    private val dateRepository: DateRepository,
    private val stringRepository: StringRepository,
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var errorMessage = ""
        var result = ""
        try {
            result = if (dateRepository.getCurrentHour() < Constants.BREAKFAST_HOUR)
                stringRepository.getStr(R.string.good_morning)
            else if (dateRepository.getCurrentHour() < Constants.LUNCH_HOUR)
                stringRepository.getStr(R.string.good_afternoon)
            else
                stringRepository.getStr(R.string.good_evening)
        } catch (exception: Exception) {
            errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
        }

        if (errorMessage.isNotEmpty()) emit(Resource.Error(message = errorMessage))
        else emit(Resource.Success(data = result))
    }
}