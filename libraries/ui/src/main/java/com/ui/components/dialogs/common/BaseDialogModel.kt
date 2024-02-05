package com.ui.components.dialogs.common

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.components.R

data class BaseDialogModel(
    val title: String,
    val backgroundColorRes: Int = R.color.white,
    val textColorRes: Int = R.color.black,
    val buttonTextRes: Int = R.string.text_button_sample,
    val onConfirmClicked: () -> Unit = {},
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {}
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Dialog
)