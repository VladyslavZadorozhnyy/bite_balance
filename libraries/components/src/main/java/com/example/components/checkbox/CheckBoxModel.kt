package com.example.components.checkbox

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class CheckBoxModel(
    val checked: Boolean,
    val active: Boolean,
    val onChecked: () -> Unit,
    val onUnchecked: () -> Unit,
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_CHECKBOX
)