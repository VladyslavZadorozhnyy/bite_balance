package com.ui.components.graph.component

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.NutritionValueModel

data class GraphModel(
    val consumption: List<NutritionValueModel>,
    val consumptionGoal: NutritionValueModel,
    val screenSpan: Int = 7
) : BaseUiComponentModel(
    componentType = ComponentUiType.Graph
)