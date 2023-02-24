package com.ui.components.dialogs.common

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class BaseDialogModel(
    val backgroundColorRes: Int,
    val textColorRes: Int,
    val title: String,
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {}
) : BaseUiComponentModel(
    componentType = ComponentUiType.Dialog
)