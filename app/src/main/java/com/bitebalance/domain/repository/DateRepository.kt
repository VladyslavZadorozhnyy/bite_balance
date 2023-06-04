package com.bitebalance.domain.repository

import com.bitebalance.domain.model.DateModel

interface DateRepository {
    fun getCurrentDate(): DateModel
    fun addDate(dateModel: DateModel): Long
}