package com.ui.basic.recycler_views.metric_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockMetricModel

open class MetricRecyclerModel(
    val items: List<MockMetricModel>,
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)

data class DishMetricsModel(
    val dishItems: List<MockMetricModel> = listOf(
        MockMetricModel(
            name = "Name:",
            editable = true
        ),
        MockMetricModel(
            name = "Prots:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Fats:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Carbs:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Kcal:",
            suffix = "kcal in 100g",
            editable = true,
            onlyNumbers = true
        )
    ),
) : MetricRecyclerModel(dishItems)


data class MealMetricsModel(
    val mealItems: List<MockMetricModel> = listOf(
        MockMetricModel(
            name = "Name:",
            editable = true
        ),
        MockMetricModel(
            name = "Prots:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Fats:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Carbs:",
            suffix = "g in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Kcal:",
            suffix = "kcal in 100g",
            editable = true,
            onlyNumbers = true
        ),
        MockMetricModel(
            name = "Eaten:",
            suffix = "in g",
            editable = true,
            onlyNumbers = true
        )
    ),
) : MetricRecyclerModel(mealItems)