package com.ui.components.graph.component

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.model.NutritionValueModel

data class GraphModel(
    val consumption: List<NutritionValueModel>,
    val consumptionGoal: NutritionValueModel,
    val foregroundColor: Int,
    val backgroundColor: Int,
    val screenSpan: Int = 7
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Graph
)