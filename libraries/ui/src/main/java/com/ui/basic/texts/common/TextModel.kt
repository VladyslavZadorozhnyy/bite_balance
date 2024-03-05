package com.ui.basic.texts.common

import android.graphics.Color
import com.ui.common.Constants
import android.graphics.drawable.Drawable
import com.ui.common.BaseUiComponentModel

data class TextModel(
    val textValue: String,
    val textSize: Int = Constants.TEXT_SIZE,
    val textColor: Int = Color.WHITE,
    val backgroundColor: Int = Color.BLACK,
    val backgroundRes: Int? = null,
    val backgroundResDrawable: Drawable? = null,
    val isSingleLine: Boolean = false,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Text
)