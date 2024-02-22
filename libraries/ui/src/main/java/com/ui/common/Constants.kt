package com.ui.common

import com.ui.components.R

object Constants {
//    Text constants
    const val TEXT_SIZE = 20
    const val TEXT_SIZE_BIG = 30
    const val TEXT_SIZE_SMALL = 15
    const val TEXT_STROKE_WIDTH = 3F

//    Icon constants
    const val ICON_SIZE = 40
    const val ICON_SIZE_MEDIUM = 80
    const val ICON_SIZE_BIG = 100
    const val CORNER_RADIUS = 20
    const val BACK_BUTTON_ICON_SIZE = 70
    const val COLOR_ICON_STROKE_WIDTH = 5

//    Other constants
    const val CAROUSEL_SIZE = 4

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