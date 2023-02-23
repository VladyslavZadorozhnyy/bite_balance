package com.example.components.graph.component

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.mocks.MockNutritionModel

data class GraphModel(
    val consumption: List<MockNutritionModel>,
    val consumptionGoal: MockNutritionModel,
    val screenSpan: Int
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_GRAPH
)