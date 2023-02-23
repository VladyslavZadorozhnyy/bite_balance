package com.example.components.graph.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.content.ContextCompat.getColor
import com.example.components.R
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.GraphLayoutBinding
import com.example.components.graph.subcomponents.ChartSubComponent
import com.example.components.graph.subcomponents.SpinnerSubComponent


class Graph(
    context: Context,
    attrs: AttributeSet? = null,
): BaseComponent(context, attrs) {
    private val spinnerItems = GraphConstants.getSpinnerItems(context)

    private val binding by lazy {
        GraphLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseComponentModel) {
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
            0 -> model.consumption.map { it.ccal ?: 0F }.toList()
            1 -> model.consumption.map { it.protein ?: 0F }.toList()
            2 -> model.consumption.map { it.fat ?: 0F }.toList()
            else -> model.consumption.map { it.carb ?: 0F }.toList()
        }
    }

    private fun indexToGoalValues(metricsIndex: Int, model: GraphModel): List<Float> {
        return when(metricsIndex) {
            0 -> model.consumption.map { model.consumptionGoal.ccal ?: 0F }.toList()
            1 -> model.consumption.map { model.consumptionGoal.protein ?: 0F }.toList()
            2 -> model.consumption.map { model.consumptionGoal.fat ?: 0F }.toList()
            else -> model.consumption.map { model.consumptionGoal.carb ?: 0F }.toList()
        }
    }
}