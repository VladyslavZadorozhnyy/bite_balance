package com.ui.basic.buttons.common

import android.view.View
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.components.R

data class ButtonModel(
    val iconRes: Int? = null,
    val iconSize: Int = ButtonConstants.ICON_SIZE,
    val labelTextRes: Int? = null,
    val labelTextSize: Int = ButtonConstants.LABEL_TEXT_SIZE,
    val strokeWidth: Int = 0,
    val foregroundColorRes: Int = R.color.white,
    val backgroundColorRes: Int = R.color.black,
    val onClickListener: View.OnClickListener? = null,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Button
)