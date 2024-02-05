package com.ui.components.progress.indicator

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants

data class ProgressIndicatorModelNew(
    val consumed: Float?,
    val goalConsumption: Float?,
    val indicatorLabel: String,
    val indicatorName: String,
    val primaryColor: Int,
    val secondaryColor: Int
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Indicator,
)