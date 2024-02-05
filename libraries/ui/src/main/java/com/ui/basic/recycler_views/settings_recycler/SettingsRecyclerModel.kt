package com.ui.basic.recycler_views.settings_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.mocks.MockInstructionModel

data class SettingsRecyclerModel(
    val items: List<MockInstructionModel>,
    val primaryColor: Int,
    val secondaryColor: Int,
    val onClickListener: (MockInstructionModel) -> Unit
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)