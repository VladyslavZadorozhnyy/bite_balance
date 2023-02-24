package com.ui.components.progress.carousel

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockNutritionModel

data class ProgressCarouselModel(
    val consumed: MockNutritionModel,
    val goalConsumption: MockNutritionModel
) : BaseUiComponentModel(
    componentType = ComponentUiType.Carousel,
)