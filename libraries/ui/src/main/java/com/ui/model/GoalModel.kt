package com.ui.model

data class GoalModel(
    val textValue: String,
    val active: Boolean,
    val achieved: Boolean,
    val dateCreatedId: Long,
    val id: Long = 0,
) {
    companion object
}