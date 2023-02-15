package com.example.components.progress.indicator

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.mocks.MockNutritionModel

data class ProgressIndicatorModel(
    val consumed: Float?,
    val goalConsumption: Float?,
    val indicatorLabel: String,
    val indicatorName: String
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_INDICATOR,
)