package com.example.components.texts.common

import com.example.components.common.ComponentConstants

data class BaseTextModel(
    val textValue: String,
    val textSize: Int = TextConstants.TEXT_SIZE,
    val textColor: Int = ComponentConstants.TEXT_COLOR,
    val backgroundColor: Int = ComponentConstants.BACKGROUND_COLOR,
)