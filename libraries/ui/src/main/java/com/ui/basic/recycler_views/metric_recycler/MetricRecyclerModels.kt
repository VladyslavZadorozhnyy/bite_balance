package com.ui.basic.recycler_views.metric_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.mocks.MockMetricModel

open class MetricRecyclerModel(
    val items: List<MockMetricModel>,
    open val foregroundColor: Int,
    open val backgroundColor: Int,
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)

data class DishNameMetricsModel(
    var dishItems: List<MockMetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(dishItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            foregroundColor: Int,
            backgroundColor: Int,
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
                ),
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
            )
        }
    }
}

data class DishMetricsModel(
    var dishItems: List<MockMetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(dishItems, foregroundColor, backgroundColor) {
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
    val mealItems: List<MockMetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            editable: Boolean = true,
            prots: Float = 0F,
            fats: Float = 0F,
            carbs: Float = 0F,
            kcal: Float = 0F,
            eaten: Float = 0F,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${prots * eaten}"
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${fats * eaten}"
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${carbs * eaten}"
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${kcal * eaten}"
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        suffix = "in g",
                        editable = editable,
                        onlyNumbers = true,
                        hint = "$eaten"
                    )
                )
            )
        }
    }
}

data class CreateMealMetricsModel(
    val mealItems: List<MockMetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            foregroundColor: Int,
            backgroundColor: Int,
            editable: Boolean = true,
            prots: Float = 0F,
            fats: Float = 0F,
            carbs: Float = 0F,
            kcal: Float = 0F,
            eaten: Float = 0F,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MockMetricModel(
                        name = "Name:",
                        editable = editable,
                        onlyNumbers = false,
                    ),
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        suffix = "in g",
                        editable = editable,
                        onlyNumbers = true,
                    )
                ),
                backgroundColor = backgroundColor,
                foregroundColor = foregroundColor,
            )
        }
    }
}

data class CreateMealWithExistingDishModel(
    val mealItems: List<MockMetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
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
                        hint = prots,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        hint = fats,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        hint = carbs,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        hint = kcal,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        suffix = "in g",
                        editable = true,
                        onlyNumbers = true,
                    )
                )
            )
        }
    }
}