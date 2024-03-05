package com.ui.components.graph.component

import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import com.ui.common.BaseUiComponentModel

data class GraphModel(
    val consumption: List<NutritionValueModel>,
    val consumptionGoal: NutritionValueModel,
    val foregroundColor: Int,
    val backgroundColor: Int,
    val screenSpan: Int = Constants.GRAPH_SPAN_SIZE,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Graph
)