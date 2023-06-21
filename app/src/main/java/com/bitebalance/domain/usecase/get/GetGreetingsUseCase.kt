package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.DateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetGreetingsUseCase(
    private val dateRepository: DateRepository
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var errorMessage = ""
        var result = ""

        try {
            result = if (dateRepository.getCurrentHour() < 12) {
                "Good morning"
            } else if (dateRepository.getCurrentHour() < 19) {
                "Good afternoon"
            } else {
                "Good evening"
            }

        } catch (exception: Exception) {
            errorMessage = exception.message ?: "Unknown exception"
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else {
            emit(Resource.Success(data = result))
        }
    }
}