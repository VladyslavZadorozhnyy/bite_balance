package com.ui.basic.recycler_views.instruction_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.mocks.MockInstructionModel

data class InstructionRecyclerModel(
    val items: List<MockInstructionModel>,
    val foregroundColor: Int,
    val backgroundColor: Int,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)