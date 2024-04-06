package com.ui.basic.buttons.common

import android.view.View
import android.graphics.Color
import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

data class ButtonModel(
    val iconRes: Int? = null,
    val iconSize: Int = Constants.ICON_SIZE,
    val labelTextRes: Int? = null,
    val labelTextSize: Int = Constants.TEXT_SIZE,
    val strokeWidth: Int = 0,
    val foregroundColor: Int = Color.WHITE,
    val backgroundColor: Int = Color.BLACK,
    val onClickListener: View.OnClickListener? = null,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Button
)