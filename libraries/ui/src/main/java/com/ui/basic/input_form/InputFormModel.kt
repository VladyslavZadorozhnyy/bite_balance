package com.ui.basic.input_form

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class InputFormModel(
    val active: Boolean,
    val hint: String = "",
    val foregroundColor: Int,
    val backgroundColor: Int,
    val onInputChange: (input: String) -> Unit = {}
) : BaseUiComponentModel(
    componentType = ComponentUiType.Input
)