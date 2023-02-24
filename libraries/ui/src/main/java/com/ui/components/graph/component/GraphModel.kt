package com.ui.components.graph.component

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockNutritionModel

data class GraphModel(
    val consumption: List<MockNutritionModel>,
    val consumptionGoal: MockNutritionModel,
    val screenSpan: Int
) : BaseUiComponentModel(
    componentType = ComponentUiType.Graph
)