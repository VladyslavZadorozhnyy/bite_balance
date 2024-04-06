package com.bitebalance.domain.usecase.get

import java.util.Date
import java.util.Calendar
import com.ui.components.R
import java.text.SimpleDateFormat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import com.bitebalance.common.Resource
import com.bitebalance.domain.repository.StringRepository


class GetMonthUseCase(
    private val stringRepository: StringRepository,
) {
    private val date = Date()

    operator fun invoke(
        dateFormat: SimpleDateFormat,
        currentMonth: String = "",
        getPrevMonth: Boolean = false,
        getNextMonth: Boolean = false,
    ): Flow<Resource<String>> = flow {
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
            errorMessage = exception.message ?: stringRepository.getStr(R.string.unknown_error)
        }

        if (errorMessage.isNotEmpty()) {
            emit(Resource.Error(message = errorMessage))
        } else {
            if (result != currentMonth || !getNextMonth)
                emit(Resource.Success(data = result))
        }
    }
}