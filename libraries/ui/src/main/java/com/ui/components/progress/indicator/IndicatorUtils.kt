package com.ui.components.progress.indicator

import com.ui.components.R

object IndicatorUtils {
    private const val MAX_PROGRESS_VALUE = 999
    private const val MAX_CONSUME_VALUE = 9999F

    private val GREEN_COLOR_RANGE = (0 until 80)
    private val YELLOW_COLOR_RANGE = (80 until 101)

    fun valueToLabel(value: Float?): String {
        value?.let {
            return if (value < MAX_CONSUME_VALUE) {
                "${value.toInt()}"
            } else {
                "${MAX_CONSUME_VALUE.toInt()}+"
            }
        }
        return " - "
    }

    fun progressToLabel(value: Int): String {
        return if (value < MAX_PROGRESS_VALUE) { "$value%" } else { "$MAX_PROGRESS_VALUE%+" }
    }

    fun progressToColor(progress: Int): Int {
        return when (progress) {
            in GREEN_COLOR_RANGE -> R.color.indicator_green
            in YELLOW_COLOR_RANGE -> R.color.indicator_yellow
            else -> R.color.indicator_red
        }
    }
}