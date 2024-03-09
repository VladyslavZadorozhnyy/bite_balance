package com.ui.basic.checkbox

import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

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