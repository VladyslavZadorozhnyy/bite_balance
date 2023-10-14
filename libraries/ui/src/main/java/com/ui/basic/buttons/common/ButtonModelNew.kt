package com.ui.basic.buttons.common

import android.graphics.Color
import android.view.View
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class ButtonModelNew(
    val iconRes: Int? = null,
    val iconSize: Int = ButtonConstants.ICON_SIZE,
    val labelTextRes: Int? = null,
    val labelTextSize: Int = ButtonConstants.LABEL_TEXT_SIZE,
    val strokeWidth: Int = 0,
    val foregroundColor: Int = Color.WHITE,
    val backgroundColor: Int = Color.BLACK,
    val onClickListener: View.OnClickListener? = null,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Button
)