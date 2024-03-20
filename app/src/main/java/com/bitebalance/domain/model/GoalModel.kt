package com.bitebalance.domain.model

import com.ui.model.GoalModel
import com.database.entities.GoalEntity

fun GoalModel.Companion.fromEntity(goalEntity: GoalEntity): GoalModel {
    return GoalModel(
        textValue = goalEntity.textValue,
        active = goalEntity.active,
        achieved = goalEntity.achieved,
        dateCreatedId = goalEntity.dateCreatedId,
        id = goalEntity.id,
    )
}

fun GoalModel.toEntity(): GoalEntity {
    return GoalEntity(
        textValue = this.textValue,
        active = this.active,
        achieved = this.achieved,
        dateCreatedId = this.dateCreatedId,
        id = this.id,
    )
}