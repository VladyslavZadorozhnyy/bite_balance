package com.ui.components.dialogs.common

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.components.R

data class BaseDialogModelNew(
    val title: String,
    val backgroundColor: Int = R.color.white,
    val textColor: Int = R.color.black,
    val buttonText: Int = R.string.text_button_sample,
    val onConfirmClicked: () -> Unit = {},
    val onPositiveClicked: () -> Unit = {},
    val onNegativeClicked: () -> Unit = {},
    val onInputConfirmed: (value: String) -> Unit = {}
) : BaseUiComponentModel(
    componentType = ComponentUiType.Dialog
)