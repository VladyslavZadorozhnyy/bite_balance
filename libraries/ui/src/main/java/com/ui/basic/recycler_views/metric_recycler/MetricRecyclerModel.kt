package com.ui.basic.recycler_views.metric_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockMetricModel

data class MetricRecyclerModel(
    val items: List<MockMetricModel>,
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)