package com.bitebalance.domain.usecase.get

import com.bitebalance.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


class GetMonthUseCase {
    private val date = Date()

    operator fun invoke(
        dateFormat: SimpleDateFormat,
        currentMonth: String = "",
        getPrevMonth: Boolean = false,
        getNextMonth: Boolean = false,
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        var errorMessage = ""
        var result = ""

        try {
            if (currentMonth.isEmpty()) {
                result = dateFormat.format(date)
            } else {
                val cal: Calendar = Calendar.getInstance().apply {
                    if (getPrevMonth) {
                        time = dateFormat.parse(currentMonth) as Date
                        add(Calendar.MONTH, -1)
                    } else if (getNextMonth) {
                        if (dateFormat.format(time) != currentMonth) {
                            time = dateFormat.parse(currentMonth) as Date
                            add(Calendar.MONTH, 1)
                        }
                    }
                }
                result = dateFormat.format(cal.time)
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