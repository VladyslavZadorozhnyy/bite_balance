package com.ui.basic.texts.common

import android.graphics.drawable.Drawable
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiConstants
import com.ui.common.ComponentUiType
import com.ui.components.R

data class TextModelNew3 (
    val textValue: String,
    val textSize: Int = ComponentUiConstants.TEXT_SIZE,
    val textColor: Int = R.color.white,
    val backgroundColor: Int = R.color.black,
    val backgroundRes: Drawable? = null
) : BaseUiComponentModel(
    componentType = ComponentUiType.Text
)