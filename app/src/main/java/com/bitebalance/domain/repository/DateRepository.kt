package com.bitebalance.domain.repository

import java.text.SimpleDateFormat
import com.bitebalance.domain.model.DateModel

interface DateRepository {
    fun getDateFromString(dateFormat: SimpleDateFormat, dateValue: String): DateModel
    fun getCurrentDate(): DateModel
    fun getDaysCountInMonth(month: Int, year: Int): Int
    fun getCurrentDateId(): Long
    fun getDateById(id: Long): DateModel?
    fun getCurrentHour(): Int
    fun removeById(id: Long)
    fun addDate(dateModel: DateModel): Long
}