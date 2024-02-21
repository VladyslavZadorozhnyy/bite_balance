package com.ui.components.dialogs.common

import com.ui.components.R
import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

data class BaseDialogModel(
    val title: String,
    val backgroundColor: Int = 0,
    val textColor: Int = 0,
    val buttonText: Int = R.string.text_button_sample,
    val onConfirmClicked: () -> Unit = {},
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {},
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Dialog
)