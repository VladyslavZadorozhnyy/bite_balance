package com.bitebalance.data.repository

import com.bitebalance.domain.model.DateModel
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.repository.DateRepository
import com.database.db.AppDaoDatabase
import java.util.*

class DateRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : DateRepository {

    override fun getCurrentDate(): DateModel {
        return DateModel(
            getCurrentMinute(),
            getCurrentHour(),
            getCurrentDay(),
            getCurrentMonth(),
            getCurrentYear()
        )
    }

    override fun addDate(dateModel: DateModel): Long {
        return appDaoDatabase.getDateDao().insert(dateModel.toEntity())
    }

    private fun getCurrentMinute(): Int {
        return Calendar.getInstance().get(Calendar.MINUTE)
    }

    private fun getCurrentHour(): Int {
        return Calendar.getInstance().get(Calendar.HOUR)
    }

    private fun getCurrentDay(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }

    private fun getCurrentMonth(): Int {
        return Calendar.getInstance().get(Calendar.MONTH)
    }

    private fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }
}