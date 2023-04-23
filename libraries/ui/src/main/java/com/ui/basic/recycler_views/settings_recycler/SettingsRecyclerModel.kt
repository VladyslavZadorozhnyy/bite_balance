package com.ui.basic.recycler_views.settings_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockInstructionModel

data class SettingsRecyclerModel(
    val items: List<MockInstructionModel>,
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)