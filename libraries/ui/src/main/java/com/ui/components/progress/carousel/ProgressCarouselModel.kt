package com.ui.components.progress.carousel

import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import com.ui.common.BaseUiComponentModel

data class ProgressCarouselModel(
    val consumed: NutritionValueModel,
    val goalConsumption: NutritionValueModel,
    val primaryColor: Int,
    val secondaryColor: Int,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Carousel,
)