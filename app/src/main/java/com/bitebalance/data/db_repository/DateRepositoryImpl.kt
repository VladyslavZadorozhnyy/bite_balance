package com.bitebalance.data.db_repository

import java.util.*
import java.text.SimpleDateFormat
import com.database.db.AppDaoDatabase
import com.bitebalance.domain.model.toEntity
import com.bitebalance.domain.model.DateModel
import com.bitebalance.domain.model.fromEntity
import com.bitebalance.domain.repository.DateRepository

class DateRepositoryImpl(
    private val appDaoDatabase: AppDaoDatabase
) : DateRepository {
    override fun getDateFromString(dateFormat: SimpleDateFormat, dateValue: String): DateModel {
        val cal: Calendar = Calendar.getInstance().apply {
            time = dateFormat.parse(dateValue) as Date
        }

        return DateModel(
            minute = 0,
            hour = 0,
            day = 1,
            month = cal.get(Calendar.MONTH),
            year = cal.get(Calendar.YEAR),
        )
    }

    override fun getCurrentDate(): DateModel {
        appDaoDatabase.getDateDao().getDate(
            day = getCurrentDay(),
            month = getCurrentMonth(),
            year = getCurrentYear(),
        )?.let { return DateModel.fromEntity(it) }

        val currentDateId = appDaoDatabase.getDateDao().insert(
            DateModel(
                minute = getCurrentMinute(),
                hour = getCurrentHour(),
                day = getCurrentDay(),
                month = getCurrentMonth(),
                year = getCurrentYear(),
            ).toEntity())

        return DateModel(
            minute = getCurrentMinute(),
            hour = getCurrentHour(),
            day = getCurrentDay(),
            month = getCurrentMonth(),
            year = getCurrentYear(),
            id = currentDateId,
        )
    }

    override fun getCurrentDateId(): Long {
        return getCurrentDate().id
    }

    override fun getDaysCountInMonth(month: Int, year: Int): Int {
        val calendar = Calendar.getInstance().also {
            it[Calendar.YEAR] = year
            it[Calendar.MONTH] = month
        }

        return calendar.getActualMaximum(Calendar.DATE)
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