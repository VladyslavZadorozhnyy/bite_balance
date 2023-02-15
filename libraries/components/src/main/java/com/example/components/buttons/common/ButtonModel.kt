package com.example.components.buttons.common

import android.view.View
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class ButtonModel(
    val iconRes: Int? = null,
    val labelTextRes: Int? = null,
    val strokeWidth: Int = 0,
    val iconSize: Int = ButtonConstants.ICON_SIZE,
    val labelTextSize: Int = ButtonConstants.LABEL_TEXT_SIZE,
    val foregroundColorRes: Int = ComponentConstants.TEXT_COLOR_RES,
    val backgroundColorRes: Int = ComponentConstants.BACKGROUND_COLOR_RES,
    val onClickListener: View.OnClickListener? = null,
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_BUTTON
)