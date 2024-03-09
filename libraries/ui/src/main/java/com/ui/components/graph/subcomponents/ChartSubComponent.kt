package com.ui.components.graph.subcomponents

import com.ui.components.R
import android.graphics.Color
import com.ui.common.Constants
import android.content.Context
import com.ui.basic.texts.common.TextModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.CombinedData
import com.ui.basic.texts.slideable_text.SlideText
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class ChartSubComponent(
    context: Context,
    private val barColor: Int,
    private val foregroundColor: Int,
    private val chartView: CombinedChart,
    private val goalConsumption: SlideText,
    private val actualConsumption: SlideText,
) {
    private val lineColor = context.getColor(R.color.indicator_green)
    private val barDataLabel = context.getString(R.string.act_consumption)
    private val lineDataLabel = context.getString(R.string.goal_consumption)

    fun setup(
        metricsLabel: String,
        barEntries: List<Float>,
        lineEntries: List<Float>,
    ) {
        val barDataSet = BarDataSet(getBarEntries(barEntries), barDataLabel).apply {
            valueTextColor = Color.TRANSPARENT
            valueTextSize = Constants.TEXT_SIZE.toFloat()
            setColors(barColor)
        }
        barDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${value.toInt()} $metricsLabel"
            }
        }
        val lineDataSet = LineDataSet(getLineEntries(lineEntries), lineDataLabel).apply {
            lineWidth = Constants.GRANULARITY * 2
            circleRadius = Constants.COLOR_ICON_STROKE_WIDTH.toFloat()
            valueTextColor = Color.TRANSPARENT

            setColors(lineColor)
            setCircleColor(lineColor)
            circleHoleColor = lineColor
        }

        val barData = BarData(barDataSet).apply { barWidth = Constants.GRANULARITY / 2 }
        val lineData = LineData(lineDataSet)

        val combinedData = CombinedData().apply {
            setData(barData)
            setData(lineData)
        }
        chartView.apply {
            description.isEnabled = false
            axisRight.isEnabled = false
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            extraBottomOffset = Constants.OFFSET_LARGE
            legend.yOffset = Constants.OFFSET_SMALL
            legend.xOffset = -Constants.OFFSET_SMALL
            legend.formSize = Constants.OFFSET_LARGE
            legend.textSize = Constants.TEXT_SIZE_SMALL.toFloat()

            xAxis.spaceMin = barData.barWidth
            xAxis.spaceMax = barData.barWidth
            xAxis.axisMinimum = Constants.GRANULARITY / 2
            axisLeft.axisMinimum = Constants.AXIS_MINIMUM

            xAxis.granularity = Constants.GRANULARITY
            xAxis.isGranularityEnabled = true

            data = combinedData
            setScaleEnabled(false)
            animateY(Constants.DURATION)
            setVisibleXRangeMaximum(Constants.GRAPH_SPAN_SIZE.toFloat())
        }
        goalConsumption.setup(
            model = TextModel(
                textValue = lineDataLabel,
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = barColor,
                backgroundColor = foregroundColor,
            ),
        )
        actualConsumption.setup(
            model = TextModel(
                textValue = barDataLabel,
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = barColor,
                backgroundColor = foregroundColor,
            ),
        )
        setOnClickListener()
    }

    private fun getBarEntries(barFloats: List<Float>): List<BarEntry> {
        return mutableListOf<BarEntry>().apply {
            for (index in barFloats.indices) { add(BarEntry(index.toFloat(), barFloats[index])) }
        }
    }

    private fun getLineEntries(lineFloats: List<Float>): List<Entry> {
        return mutableListOf<BarEntry>().apply {
            for (index in lineFloats.indices) { add(BarEntry(index.toFloat(), lineFloats[index])) }
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

            override fun onNothingSelected() { barDs.valueTextColor = Color.TRANSPARENT }
        })
    }

    private fun updateDataSetColors(chosenIndex: Int, entryCount: Int): List<Int> {
        val result = arrayListOf<Int>()

        for (i in 0 .. entryCount)
            result.add(if (i == chosenIndex) barColor else Color.TRANSPARENT)
        return result
    }
}