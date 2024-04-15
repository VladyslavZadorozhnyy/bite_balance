package com.ui.components.graph.subcomponents

import android.view.View
import com.ui.components.R
import android.graphics.Color
import com.ui.common.Constants
import android.content.Context
import com.ui.basic.texts.common.TextModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.BarData
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.LineData
import com.ui.basic.texts.slideable_text.SlideText
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class ChartSubComponent(
    private val context: Context,
    private val foregroundColor: Int,
    private val backgroundColor: Int,
    private val chartView: CombinedChart,
    private val goalConsumption: SlideText,
    private val actualConsumption: SlideText,
    private val goalConsumptionSign: View,
    private val actualConsumptionSign: View,
) {
    private val lineColor = context.getColor(R.color.indicator_green)
    private val barColor = context.getColor(R.color.gray)
    private val barDataLabel = context.getString(R.string.act_consumption)
    private val lineDataLabel = context.getString(R.string.goal_consumption)

    fun setup(
        barEntries: List<Float>,
        lineEntries: List<Float>,
    ) {
        val barDataSet = BarDataSet(getNormalizedEntries(barEntries, lineEntries), barDataLabel).apply {
            valueTextColor = Color.TRANSPARENT
            valueTextSize = Constants.TEXT_SIZE.toFloat()
            setColors(barColor)
        }
        barDataSet.valueTypeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)
        barDataSet.valueTextSize = Constants.TEXT_SIZE_EXTRA_SMALL
        barDataSet.formLineWidth = Constants.TEXT_SIZE_EXTRA_SMALL
        barDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(normalizedValue: Float): String {
                val goalValue = lineEntries[0]
                val consumedValue = normalizedValue * goalValue / 100F
                return context.getString(R.string.out_of_label, consumedValue, goalValue, normalizedValue)
            }
        }
        val lineDataSet = LineDataSet(getNormalizedEntries(lineEntries, lineEntries), lineDataLabel).apply {
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

            extraLeftOffset = Constants.OFFSET_LARGE
            extraRightOffset = Constants.OFFSET_LARGE * 2
            extraBottomOffset = Constants.OFFSET_LARGE
            legend.yOffset = Constants.OFFSET_SMALL
            legend.xOffset = -Constants.OFFSET_MEDIUM
            legend.formSize = Constants.AXIS_MINIMUM
            legend.textSize = Constants.TEXT_SIZE_SMALL.toFloat()
            legend.textColor = foregroundColor

            xAxis.spaceMin = barData.barWidth
            xAxis.spaceMax = barData.barWidth
            xAxis.axisMinimum = Constants.GRANULARITY / 2
            axisLeft.axisMinimum = Constants.AXIS_MINIMUM

            xAxis.granularity = Constants.GRANULARITY
            xAxis.isGranularityEnabled = true
            xAxis.textColor = backgroundColor
            xAxis.typeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)
            axisLeft.textColor = backgroundColor
            axisLeft.typeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)

            data = combinedData
            setScaleEnabled(false)
            animateY(Constants.DURATION)
            setVisibleXRangeMaximum(Constants.GRAPH_SPAN_SIZE.toFloat())
            chartView.visibility = View.VISIBLE
        }
        goalConsumption.setup(
            model = TextModel(
                textValue = lineDataLabel,
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = backgroundColor,
                backgroundColor = foregroundColor,
            ),
        )
        goalConsumptionSign.setBackgroundColor(lineColor)
        actualConsumption.setup(
            model = TextModel(
                textValue = barDataLabel,
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = backgroundColor,
                backgroundColor = foregroundColor,
            ),
        )
        actualConsumptionSign.setBackgroundColor(barColor)
        setOnClickListener()
    }

    private fun getNormalizedEntries(barVal: List<Float>, lineVal: List<Float>): List<BarEntry> {
        return mutableListOf<BarEntry>().apply {
            for (i in barVal.indices) {
                val normalizedValue = if (lineVal[i] == 0F) 0F else barVal[i] / lineVal[i] * 100F
                add(BarEntry(i.toFloat() + 1, normalizedValue)) }
        }
    }

    private fun setOnClickListener() {
        chartView.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            private val barDs = chartView.data.dataSets[1]

            override fun onValueSelected(e: Entry?, h: Highlight?) {
                e?.x?.toInt()?.let { entryX ->
                    barDs.setValueTextColors(updateDataSetColors(entryX - 1, barDs.entryCount))
                }
            }

            override fun onNothingSelected() { barDs.valueTextColor = Color.TRANSPARENT }
        })
    }

    private fun updateDataSetColors(chosenIndex: Int, entryCount: Int): List<Int> {
        val result = arrayListOf<Int>()

        for (i in 0 .. entryCount)
            result.add(if (i == chosenIndex) backgroundColor else Color.TRANSPARENT)
        return result
    }
}