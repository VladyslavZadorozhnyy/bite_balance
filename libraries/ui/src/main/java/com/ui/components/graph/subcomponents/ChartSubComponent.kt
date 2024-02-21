package com.ui.components.graph.subcomponents

import android.content.Context
import androidx.core.content.ContextCompat.getColor
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.slideable_text.SlideableText
import com.ui.components.R

class ChartSubComponent(
    context: Context,
    private val barColor: Int,
    private val foregroundColor: Int,
    private val chartView: CombinedChart,
    private val goalConsumption: SlideableText,
    private val actualConsumption: SlideableText
) {
    private val barDataLabel = context.getString(R.string.act_consumption)
    private val lineDataLabel = context.getString(R.string.goal_consumption)

    private val lineColor = getColor(context, R.color.indicator_green)
    private val transparentColor = getColor(context, R.color.transparent)

    fun setup(
        metricsLabel: String,
        barEntries: List<Float>,
        lineEntries: List<Float>
    ) {
        val barDataSet = BarDataSet(getBarEntries(barEntries), barDataLabel).also {
            it.valueTextColor = transparentColor
            it.setColors(barColor)
            it.valueTextSize = 16f
        }

        barDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()} $metricsLabel"
            }
        }

        val lineDataSet = LineDataSet(getLineEntries(lineEntries), lineDataLabel).also {
            it.lineWidth = 2f
            it.circleRadius = 5f
            it.valueTextColor = transparentColor

            it.setColors(lineColor)
            it.setCircleColor(lineColor)
            it.circleHoleColor = lineColor
        }

        val barData = BarData(barDataSet).also { it.barWidth = 0.5f }
        val lineData = LineData(lineDataSet)

        val combinedData = CombinedData().also {
            it.setData(barData)
            it.setData(lineData)
        }

        chartView.apply {
            description.isEnabled = false
            axisRight.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            extraBottomOffset = 20f
            legend.yOffset = 10f
            legend.xOffset = -10f
            legend.formSize = 20f
            legend.textSize = 15f

            xAxis.spaceMin = barData.barWidth
            xAxis.spaceMax = barData.barWidth
            xAxis.axisMinimum = 0.5F
            axisLeft.axisMinimum = 0F

            xAxis.granularity = 1.0f
            xAxis.isGranularityEnabled = true

            data = combinedData
            setScaleEnabled(false)
            animateY(2000)
            setVisibleXRangeMaximum(7F)
        }

        goalConsumption.setup(
            model = TextModel(
                textValue = lineDataLabel,
                textSize = 15,
                textColor = barColor,
                backgroundColor = foregroundColor,
            )
        )

        actualConsumption.setup(
            model = TextModel(
                textValue = barDataLabel,
                textSize = 15,
                textColor = barColor,
                backgroundColor = foregroundColor,
            )
        )

        setOnClickListener()
    }

    private fun getBarEntries(barFloats: List<Float>): List<BarEntry> {
        return mutableListOf<BarEntry>().apply {
            for (index in barFloats.indices) {
                add(BarEntry(index.toFloat(), barFloats[index]))
            }
        }
    }

    private fun getLineEntries(lineFloats: List<Float>): List<Entry> {
        return mutableListOf<BarEntry>().apply {
            for (index in lineFloats.indices) {
                add(BarEntry(index.toFloat(), lineFloats[index]))
            }
        }
    }

    private fun setOnClickListener() {
        chartView.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            private val barDs = chartView.data.dataSets[1]

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                e?.x?.toInt()?.let { entryX ->
                    barDs.setValueTextColors(updateDataSetColors(entryX, barDs.entryCount))
                }
            }

            override fun onNothingSelected() {
                barDs.valueTextColor = transparentColor
            }
        })
    }

    private fun updateDataSetColors(chosenIndex: Int, entryCount: Int): List<Int> {
        val result = arrayListOf<Int>()

        for (i in 0 .. entryCount) {
            result.add(if (i == chosenIndex) { barColor } else { transparentColor })
        }
        return result
    }
}