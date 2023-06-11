package com.ui.components.dialogs.common

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.components.R

data class BaseDialogModel(
    val backgroundColorRes: Int,
    val textColorRes: Int,
    val title: String,
    val buttonTextRes: Int = R.string.text_button_sample,
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {}
) : BaseUiComponentModel(
    componentType = ComponentUiType.Dialog
)