package com.ui.basic.recycler_views.goal_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockGoalModel

data class GoalRecyclerModel(
    val items: List<MockGoalModel>,
    val backgroundColor: Int,
    val foregroundColor: Int,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)