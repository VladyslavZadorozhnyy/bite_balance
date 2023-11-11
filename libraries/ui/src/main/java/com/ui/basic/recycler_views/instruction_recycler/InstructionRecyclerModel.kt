package com.ui.basic.recycler_views.instruction_recycler

import android.graphics.Color
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockInstructionModel

data class InstructionRecyclerModel(
    val items: List<MockInstructionModel>,
    val foregroundColor: Int,
    val backgroundColor: Int,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)