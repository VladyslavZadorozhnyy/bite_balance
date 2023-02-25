package com.ui.basic.recycler_views.goal_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class GoalRecyclerModel(
    val items: List<String>,
    val backgroundColorRes: Int,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)