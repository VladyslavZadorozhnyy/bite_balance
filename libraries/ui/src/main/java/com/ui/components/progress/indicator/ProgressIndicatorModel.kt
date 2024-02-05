package com.ui.components.progress.indicator

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants

data class ProgressIndicatorModel(
    val consumed: Float?,
    val goalConsumption: Float?,
    val indicatorLabel: String,
    val indicatorName: String
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Indicator,
)