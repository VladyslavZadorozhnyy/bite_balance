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
        entity.minute,
        entity.hour,
        entity.day,
        entity.month,
        entity.year,
        entity.additions,
        entity.id,
    )
}

fun DateModel.toEntity(): DateEntity {
    return DateEntity(
        this.minute,
        this.hour,
        this.day,
        this.month,
        this.year,
        this.additions
    )
}