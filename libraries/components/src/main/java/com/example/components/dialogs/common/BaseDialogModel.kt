package com.example.components.dialogs.common

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class BaseDialogModel(
    val aaadipVal: Boolean
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_DIALOG
)