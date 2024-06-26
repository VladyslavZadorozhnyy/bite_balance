package com.ui.basic.recycler_views.settings_recycler

import com.ui.common.Constants
import com.ui.model.InstructionModel
import com.ui.common.BaseUiComponentModel

data class SettingsRecyclerModel(
    val items: List<InstructionModel>,
    val primaryColor: Int,
    val secondaryColor: Int,
    val onClickListener: (InstructionModel) -> Unit,
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)