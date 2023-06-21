package com.bitebalance.domain.repository

import com.bitebalance.domain.model.DateModel

interface DateRepository {
    fun getCurrentDate(): DateModel
    fun getCurrentDateId(): Long
    fun getDateById(id: Long): DateModel?
    fun getCurrentHour(): Int
    fun addDate(dateModel: DateModel): Long
}