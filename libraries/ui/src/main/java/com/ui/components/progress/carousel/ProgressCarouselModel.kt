package com.ui.components.progress.carousel

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.model.NutritionValueModel

data class ProgressCarouselModel(
    val consumed: NutritionValueModel,
    val goalConsumption: NutritionValueModel
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Carousel,
)