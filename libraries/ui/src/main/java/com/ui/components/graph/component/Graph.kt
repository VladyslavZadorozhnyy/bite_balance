package com.ui.components.graph.component

import android.content.Context
import android.content.res.ColorStateList
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
            SpinnerSubComponent(binding.spinner).setup(
                context,
                spinnerItems,
                model.foregroundColor,
                model.backgroundColor,
            ) { activeIndex ->
                ChartSubComponent(
                    context,
                    graphModel.foregroundColor,
                    graphModel.backgroundColor,
                    binding.chartView,
                    binding.goalConsumption,
                    binding.actualConsumption,
                ).setup(
                    indexToLabel(activeIndex),
                    indexToConsumptionValues(activeIndex, graphModel),
                    indexToGoalValues(activeIndex, graphModel)
                )
                binding.chartView.backgroundTintList =
                    ColorStateList.valueOf(graphModel.backgroundColor)
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
            0 -> model.consumption.map { it.kcals }.toList()
            1 -> model.consumption.map { it.prots }.toList()
            2 -> model.consumption.map { it.fats }.toList()
            else -> model.consumption.map { it.carbs }.toList()
        }
    }

    private fun indexToGoalValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when(metricsIndex) {
            0 -> model.consumption.map { model.consumptionGoal.kcals }.toList()
            1 -> model.consumption.map { model.consumptionGoal.prots }.toList()
            2 -> model.consumption.map { model.consumptionGoal.fats }.toList()
            else -> model.consumption.map { model.consumptionGoal.carbs }.toList()
        }
    }
}