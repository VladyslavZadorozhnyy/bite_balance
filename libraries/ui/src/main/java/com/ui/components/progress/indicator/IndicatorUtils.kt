package com.ui.components.progress.indicator

import com.ui.components.R
import com.ui.common.Constants.GREEN_COLOR_RANGE
import com.ui.common.Constants.MAX_CONSUME_VALUE
import com.ui.common.Constants.MAX_PROGRESS_VALUE
import com.ui.common.Constants.YELLOW_COLOR_RANGE

object IndicatorUtils {

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