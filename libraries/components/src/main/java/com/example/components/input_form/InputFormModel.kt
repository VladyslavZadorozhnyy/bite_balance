package com.example.components.input_form

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class InputFormModel(
    val active: Boolean,
    val hint: String = "",
    val onInputChange: (input: String) -> Unit = {}
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_INPUT
)