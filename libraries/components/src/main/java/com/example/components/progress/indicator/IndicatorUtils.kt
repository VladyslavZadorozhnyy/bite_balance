package com.example.components.progress.indicator

import com.example.components.progress.carousel.CarouselConstants.GREEN_COLOR
import com.example.components.progress.carousel.CarouselConstants.GREEN_COLOR_RANGE
import com.example.components.progress.carousel.CarouselConstants.RED_COLOR
import com.example.components.progress.carousel.CarouselConstants.YELLOW_COLOR
import com.example.components.progress.carousel.CarouselConstants.YELLOW_COLOR_RANGE

object IndicatorUtils {
    private const val MAX_PROGRESS_VALUE = 999
    private const val MAX_CONSUME_VALUE = 9999F

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
            in GREEN_COLOR_RANGE -> GREEN_COLOR
            in YELLOW_COLOR_RANGE -> YELLOW_COLOR
            else -> RED_COLOR
        }
    }
}