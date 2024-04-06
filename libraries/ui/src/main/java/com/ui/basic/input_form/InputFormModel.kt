package com.ui.basic.input_form

import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel


data class InputFormModel(
    val active: Boolean,
    val hint: String = "",
    val foregroundColor: Int,
    val backgroundColor: Int,
    val hintGravity: Int? = null,
    val onInputChange: (input: String) -> Unit = {},
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Input
)