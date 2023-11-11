package com.ui.components.progress.carousel

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.NutritionValueModel

data class ProgressCarouselModelNew(
    val consumed: NutritionValueModel,
    val goalConsumption: NutritionValueModel,
    val primaryColor: Int,
    val secondaryColor: Int,
) : BaseUiComponentModel(
    componentType = ComponentUiType.Carousel,
)