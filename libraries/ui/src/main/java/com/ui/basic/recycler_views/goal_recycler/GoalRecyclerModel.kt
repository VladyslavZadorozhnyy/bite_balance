package com.ui.basic.recycler_views.goal_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.GoalModel

data class GoalRecyclerModel(
    val items: List<GoalModel>,
    val backgroundColor: Int,
    val foregroundColor: Int,
    val goalAdapterListener: GoalAdapter.GoalAdapterListener,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)