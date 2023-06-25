package com.bitebalance.data.repository

import com.bitebalance.domain.model.DateModel
import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.repository.DateRepository
import com.database.db.AppDaoDatabase
import java.util.*

class DateRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : DateRepository {

    override fun getCurrentDate(): DateModel {
        appDaoDatabase.getDateDao().getDate(
            getCurrentDay(),
            getCurrentMonth(),
            getCurrentYear(),
        )?.let { return DateModel.fromEntity(it) }

        val currentDateId = appDaoDatabase.getDateDao().insert(
            DateModel(
                getCurrentMinute(),
                getCurrentHour(),
                getCurrentDay(),
                getCurrentMonth(),
                getCurrentYear(),
            ).toEntity())

        return DateModel(
            getCurrentMinute(),
            getCurrentHour(),
            getCurrentDay(),
            getCurrentMonth(),
            getCurrentYear(),
            id = currentDateId
        )
    }

    override fun getCurrentDateId(): Long {
        return getCurrentDate().id
    }

    override fun addDate(dateModel: DateModel): Long {
        return appDaoDatabase.getDateDao().insert(dateModel.toEntity())
    }

    override fun getDateById(id: Long): DateModel? {
        return appDaoDatabase.getDateDao().getById(id)?.let { DateModel.fromEntity(it) }
    }

    override fun getCurrentHour(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    }

    override fun removeById(id: Long) {
        appDaoDatabase.getDateDao().deleteById(id)
    }

    private fun getCurrentMinute(): Int {
        return Calendar.getInstance().get(Calendar.MINUTE)
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