package com.bitebalance.domain.model

import com.database.entities.GoalEntity
import com.ui.model.GoalModel

fun GoalModel.Companion.fromEntity(goalEntity: GoalEntity): GoalModel {
    return GoalModel(
        goalEntity.textValue,
        goalEntity.active,
        goalEntity.achieved,
        goalEntity.dateCreatedId,
        goalEntity.id,
    )
}

fun GoalModel.toEntity(): GoalEntity {
    return GoalEntity(
        this.textValue,
        this.active,
        this.achieved,
        this.dateCreatedId,
        this.id,
    )
}