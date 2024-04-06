package com.ui.basic.recycler_views.instruction_recycler

import com.ui.common.Constants
import com.ui.model.InstructionModel
import com.ui.common.BaseUiComponentModel

data class InstructionRecyclerModel(
    val items: List<InstructionModel>,
    val foregroundColor: Int,
    val backgroundColor: Int,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)