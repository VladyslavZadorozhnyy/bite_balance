package com.ui.basic.checkbox

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants

data class CheckBoxModel(
    val checked: Boolean,
    val active: Boolean,
    val onChecked: () -> Unit,
    val onUnchecked: () -> Unit,
    val backgroundColor: Int,
    val foregroundColor: Int
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Checkbox
)