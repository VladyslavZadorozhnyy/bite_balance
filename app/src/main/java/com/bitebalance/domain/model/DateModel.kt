package com.bitebalance.domain.model

import com.database.entities.DateEntity

data class DateModel(
    val minute: Int,
    val hour: Int,
    val day: Int,
    val month: Int,
    val year: Int,
    val additions: String = "",
    val id: Long = -1,
) { companion object }

fun DateModel.Companion.fromEntity(entity: DateEntity): DateModel {
    return DateModel(
        minute = entity.minute,
        hour = entity.hour,
        day = entity.day,
        month = entity.month,
        year = entity.year,
        additions = entity.additions,
        id = entity.id,
    )
}

fun DateModel.toEntity(): DateEntity {
    return DateEntity(
        minute = this.minute,
        hour = this.hour,
        day = this.day,
        month = this.month,
        year = this.year,
        additions = this.additions,
    )
}

fun DateModel.same(model: DateModel): Boolean {
    return this.year == model.year
            && this.month == model.month
            && this.day == model.day
}