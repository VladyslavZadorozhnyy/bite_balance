package com.ui.components.graph.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.graph.subcomponents.ChartSubComponent
import com.ui.components.graph.subcomponents.SpinnerSubComponent
import com.ui.components.databinding.GraphLayoutBinding


class Graph(
    context: Context,
    attrs: AttributeSet? = null,
): BaseUiComponent(context, attrs) {
    private val spinnerItems = GraphConstants.getSpinnerItems(context)

    private val binding by lazy {
        GraphLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? GraphModel)?.let { graphModel ->
            SpinnerSubComponent(binding.spinner).setup(context, spinnerItems) { activeIndex ->
                ChartSubComponent(context, binding.chartView).setup(
                    indexToLabel(activeIndex),
                    indexToConsumptionValues(activeIndex, graphModel),
                    indexToGoalValues(activeIndex, graphModel)
                )
            }
        }
    }

    private fun indexToLabel(metricsIndex: Int): String {
        return when(metricsIndex) {
            0 -> "kcal"
            else -> "g"
        }
    }

    private fun indexToConsumptionValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when(metricsIndex) {
            0 -> model.consumption.map { it.kcal ?: 0F }.toList()
            1 -> model.consumption.map { it.protein ?: 0F }.toList()
            2 -> model.consumption.map { it.fat ?: 0F }.toList()
            else -> model.consumption.map { it.carb ?: 0F }.toList()
        }
    }

    private fun indexToGoalValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when(metricsIndex) {
            0 -> model.consumption.map { model.consumptionGoal.kcal ?: 0F }.toList()
            1 -> model.consumption.map { model.consumptionGoal.protein ?: 0F }.toList()
            2 -> model.consumption.map { model.consumptionGoal.fat ?: 0F }.toList()
            else -> model.consumption.map { model.consumptionGoal.carb ?: 0F }.toList()
        }
    }
}