package com.ui.components.graph.component

import com.ui.components.R
import android.content.Context
import com.ui.common.Constants
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.GraphLayoutBinding
import com.ui.components.graph.subcomponents.ChartSubComponent
import com.ui.components.graph.subcomponents.SpinnerSubComponent


class Graph(
    context: Context,
    attrs: AttributeSet? = null,
): BaseUiComponent(context, attrs) {
    private val binding by lazy {
        GraphLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? GraphModel)?.let { graphModel ->
            SpinnerSubComponent(binding.spinner).setup(
                context = context,
                spinnerItems = Constants.SPINNER_ITEMS,
                foregroundColor = model.foregroundColor,
                backgroundColor = model.backgroundColor,
            ) { activeIndex ->
                ChartSubComponent(
                    context = context,
                    foregroundColor = graphModel.backgroundColor,
                    backgroundColor = graphModel.foregroundColor,
                    chartView = binding.chartView,
                    goalConsumption = binding.goalConsumption,
                    actualConsumption = binding.actualConsumption,
                ).setup(
                    barEntries = indexToConsumptionValues(activeIndex, graphModel),
                    lineEntries = indexToGoalValues(activeIndex, graphModel),
                )
                binding.chartView.backgroundTintList =
                    ColorStateList.valueOf(graphModel.backgroundColor)
            }
        }
    }

    private fun indexToLabel(metricsIndex: Int): String {
        return context.getString(if (metricsIndex == 0) R.string.kcal else R.string.g)
    }

    private fun indexToConsumptionValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when (metricsIndex) {
            0 -> model.consumption.map { it.kcals }.toList()
            1 -> model.consumption.map { it.prots }.toList()
            2 -> model.consumption.map { it.fats }.toList()
            else -> model.consumption.map { it.carbs }.toList()
        }
    }

    private fun indexToGoalValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when (metricsIndex) {
            0 -> model.consumption.map { model.consumptionGoal.kcals }.toList()
            1 -> model.consumption.map { model.consumptionGoal.prots }.toList()
            2 -> model.consumption.map { model.consumptionGoal.fats }.toList()
            else -> model.consumption.map { model.consumptionGoal.carbs }.toList()
        }
    }
}