package com.ui.basic.recycler_views.metric_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockMetricModel

open class MetricRecyclerModel(
    val items: List<MockMetricModel>,
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)

data class DishNameMetricsModel(
    var dishItems: List<MockMetricModel> = listOf()
) : MetricRecyclerModel(dishItems) {
    companion object {
        fun newInstance(
            name: String = "",
            prots: String = "",
            fats: String = "",
            carbs: String = "",
            kcal: String = "",
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MockMetricModel(
                        name = "Name:",
                        editable = true,
                        hint = name,
                    ),
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true,
                        hint = prots,
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true,
                        hint = fats,
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true,
                        hint = carbs,
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = true,
                        onlyNumbers = true,
                        hint = kcal,
                    )
                )
            )
        }
    }
}

data class DishMetricsModel(
    var dishItems: List<MockMetricModel> = listOf()
) : MetricRecyclerModel(dishItems) {
    companion object {
        fun newInstance(
            editable: Boolean,
            prots: String = "",
            fats: String = "",
            carbs: String = "",
            kcal: String = "",
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = prots,
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = fats,
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = carbs,
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = kcal,
                    )
                )
            )
        }
    }
}

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