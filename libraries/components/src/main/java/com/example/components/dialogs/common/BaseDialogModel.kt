package com.example.components.dialogs.common

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class BaseDialogModel(
    val backgroundColorRes: Int,
    val textColor: Int,
    val title: String,
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {}
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_DIALOG
)