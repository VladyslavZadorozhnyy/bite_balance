package com.ui.components.progress.carousel

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.NutritionValueModel

data class ProgressCarouselModel(
    val consumed: NutritionValueModel,
    val goalConsumption: NutritionValueModel
) : BaseUiComponentModel(
    componentType = ComponentUiType.Carousel,
)