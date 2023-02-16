package com.example.components.checkbox

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class CheckBoxModel(
    val state: Int
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_CHECKBOX
)