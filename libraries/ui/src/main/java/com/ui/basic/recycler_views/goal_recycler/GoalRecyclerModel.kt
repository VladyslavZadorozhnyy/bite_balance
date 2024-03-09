package com.ui.basic.recycler_views.goal_recycler

import com.ui.model.GoalModel
import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

data class GoalRecyclerModel(
    val items: List<GoalModel>,
    val backgroundColor: Int,
    val foregroundColor: Int,
    val goalAdapterListener: GoalAdapter.GoalAdapterListener,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler,
)