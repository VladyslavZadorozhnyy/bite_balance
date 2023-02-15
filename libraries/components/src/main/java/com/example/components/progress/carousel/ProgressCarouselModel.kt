package com.example.components.progress.carousel

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.mocks.MockNutritionModel

data class ProgressCarouselModel(
    val consumed: MockNutritionModel,
    val goalConsumption: MockNutritionModel
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_CAROUSEL,
)