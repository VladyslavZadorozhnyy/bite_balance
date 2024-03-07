package com.ui.common

import java.util.*
import com.ui.components.R
import java.text.SimpleDateFormat

object Constants {
//    Text constants
    const val TEXT_SIZE = 20
    const val TEXT_SIZE_MD = 25
    const val TEXT_SIZE_BIG = 30
    const val TEXT_SIZE_SMALL = 15
    const val TEXT_STROKE_WIDTH = 3F

//    Icon constants
    const val ICON_SIZE = 40
    const val ICON_SIZE_MEDIUM = 80
    const val ICON_SIZE_BIG = 100
    const val ICON_SIZE_LARGE = 120
    const val CORNER_RADIUS = 20
    const val BACK_BUTTON_ICON_SIZE = 70
    const val COLOR_ICON_STROKE_WIDTH = 5

//    Offset constants
    const val OFFSET_SMALL = 10f
    const val OFFSET_LARGE = 30f
    const val GRANULARITY = 1f
    const val DURATION = 2000
    const val AXIS_MINIMUM = 0F

//    Indicator constants
    const val MAX_PROGRESS_VALUE = 999
    const val MAX_CONSUME_VALUE = 9999F

    val GREEN_COLOR_RANGE = (0 until 80)
    val YELLOW_COLOR_RANGE = (80 until 101)

//    Other constants
    const val CAROUSEL_SIZE = 4
    const val GRAPH_SPAN_SIZE = 7
    const val DOT_SIZE_DP = 12
    const val DOT_STROKE_PX = 10
    const val GS_STANDARD = 100f

    val NAVIGATION_ICONS_LIST = listOf(
        R.drawable.nav_home_active,
        R.drawable.nav_stats_active,
        R.drawable.nav_menu_active,
        R.drawable.nav_settings_active,
    )

    val SPINNER_ITEMS = listOf(
        R.string.kcal_consumption,
        R.string.prots_consumption,
        R.string.fats_consumption,
        R.string.carbs_consumption,
    )

    val DATE_FORMAT = SimpleDateFormat("MMMM yyyy", Locale.US)

    enum class ComponentUiType {
        Button,
        Text,
        Indicator,
        Carousel,
        NavBar,
        Checkbox,
        Input,
        Dialog,
        Graph,
        Recycler,
    }
}