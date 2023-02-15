package com.example.components.texts.common

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class TextModel(
    val textValue: String,
    val textSize: Int = TextConstants.TEXT_SIZE,
    val textColor: Int = ComponentConstants.TEXT_COLOR,
    val backgroundColor: Int = ComponentConstants.BACKGROUND_COLOR,
    val backgroundRes: Int? = null
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_TEXT
)