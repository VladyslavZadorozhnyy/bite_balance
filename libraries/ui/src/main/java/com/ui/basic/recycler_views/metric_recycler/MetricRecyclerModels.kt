package com.ui.basic.recycler_views.metric_recycler

import com.ui.components.R
import android.content.Context
import com.ui.common.Constants
import com.ui.model.MetricModel
import com.ui.common.BaseUiComponentModel

open class MetricRecyclerModel(
    val items: List<MetricModel>,
    open val foregroundColor: Int,
    open val backgroundColor: Int,
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)

data class DishNameMetricsModel(
    var dishItems: List<MetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(dishItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            context: Context,
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
                    MetricModel(
                        name = context.getString(R.string.name_),
                        editable = true,
                        hint = name,
                    ),
                    MetricModel(
                        name = context.getString(R.string.prots_),
                        suffix = context.getString(R.string.g_in),
                        editable = true,
                        onlyNumbers = true,
                        hint = prots,
                    ),
                    MetricModel(
                        name = context.getString(R.string.fats_),
                        suffix = context.getString(R.string.g_in),
                        editable = true,
                        onlyNumbers = true,
                        hint = fats,
                    ),
                    MetricModel(
                        name = context.getString(R.string.carbs_),
                        suffix = context.getString(R.string.g_in),
                        editable = true,
                        onlyNumbers = true,
                        hint = carbs,
                    ),
                    MetricModel(
                        name = context.getString(R.string.kcal_),
                        suffix = context.getString(R.string.kcal_in),
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
    var dishItems: List<MetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(dishItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            context: Context,
            editable: Boolean,
            prots: String = "",
            fats: String = "",
            carbs: String = "",
            kcal: String = "",
            foregroundColor: Int = 0,
            backgroundColor: Int = 0,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MetricModel(
                        name = context.getString(R.string.prots_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = prots,
                    ),
                    MetricModel(
                        name = context.getString(R.string.fats_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = fats,
                    ),
                    MetricModel(
                        name = context.getString(R.string.carbs_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = carbs,
                    ),
                    MetricModel(
                        name = context.getString(R.string.kcal_),
                        suffix = context.getString(R.string.kcal_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = kcal,
                    ),
                ),
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
            )
        }
    }
}

data class MealMetricsModel(
    val mealItems: List<MetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            context: Context,
            editable: Boolean = true,
            prots: Float = 0F,
            fats: Float = 0F,
            carbs: Float = 0F,
            kcal: Float = 0F,
            eaten: Float = 0F,
            backgroundColor: Int = 0,
            foregroundColor: Int = 0,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                dishItems = listOf(
                    MetricModel(
                        name = context.getString(R.string.prots_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${prots * eaten}"
                    ),
                    MetricModel(
                        name = context.getString(R.string.fats_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${fats * eaten}"
                    ),
                    MetricModel(
                        name = context.getString(R.string.carbs_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${carbs * eaten}"
                    ),
                    MetricModel(
                        name = context.getString(R.string.kcal_),
                        suffix = context.getString(R.string.kcal_in),
                        editable = editable,
                        onlyNumbers = true,
                        hint = "${kcal * eaten}"
                    ),
                    MetricModel(
                        name = context.getString(R.string.eaten_),
                        suffix = context.getString(R.string.in_g),
                        editable = editable,
                        onlyNumbers = true,
                        hint = "$eaten"
                    ),
                ),
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
            )
        }
    }
}

data class CreateMealMetricsModel(
    val mealItems: List<MetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            context: Context,
            foregroundColor: Int,
            backgroundColor: Int,
            editable: Boolean = true,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MetricModel(
                        name = context.getString(R.string.name_),
                        editable = editable,
                        onlyNumbers = false,
                    ),
                    MetricModel(
                        name = context.getString(R.string.prots_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.fats_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.carbs_),
                        suffix = context.getString(R.string.g_in),
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.kcal_),
                        suffix = context.getString(R.string.kcal_in),
                        editable = editable,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.eaten_),
                        suffix = context.getString(R.string.in_g),
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
    val mealItems: List<MetricModel> = listOf(),
    override val foregroundColor: Int = 0,
    override val backgroundColor: Int = 0,
) : MetricRecyclerModel(mealItems, foregroundColor, backgroundColor) {
    companion object {
        fun newInstance(
            context: Context,
            prots: String = "",
            fats: String = "",
            carbs: String = "",
            kcal: String = "",
            foregroundColor: Int = 0,
            backgroundColor: Int = 0,
        ): DishNameMetricsModel {
            return DishNameMetricsModel(
                listOf(
                    MetricModel(
                        name = context.getString(R.string.prots_),
                        suffix = context.getString(R.string.g_in),
                        hint = prots,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.fats_),
                        suffix = context.getString(R.string.g_in),
                        hint = fats,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.carbs_),
                        suffix = context.getString(R.string.g_in),
                        hint = carbs,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.kcal_),
                        suffix = context.getString(R.string.kcal_in),
                        hint = kcal,
                        editable = false,
                        onlyNumbers = true,
                    ),
                    MetricModel(
                        name = context.getString(R.string.eaten_),
                        suffix = context.getString(R.string.in_g),
                        editable = true,
                        onlyNumbers = true,
                    )
                ),
                foregroundColor = foregroundColor,
                backgroundColor = backgroundColor,
            )
        }
    }
}