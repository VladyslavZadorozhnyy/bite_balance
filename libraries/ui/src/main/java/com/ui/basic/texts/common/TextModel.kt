package com.ui.basic.texts.common

import android.graphics.drawable.Drawable
import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.components.R

data class TextModel(
    val textValue: String,
    val textSize: Int = Constants.TEXT_SIZE,
    val textColor: Int = R.color.white,
    val backgroundColor: Int = R.color.black,
    val backgroundRes: Int? = null,
    val backgroundResDrawable: Drawable? = null,
    val isSingleLine: Boolean = false,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Text
)