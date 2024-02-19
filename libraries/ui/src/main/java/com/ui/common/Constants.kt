package com.ui.common

import com.ui.components.R

object Constants {
    const val TEXT_STROKE_WIDTH = 3F
    const val CAROUSEL_SIZE = 4

//    Text constants
    const val TEXT_SIZE = 20
    const val TEXT_SIZE_BIG = 30

//    Button constants
    const val BACK_BUTTON_ICON_SIZE = 70




    const val ICON_SIZE = 40
    const val CORNER_RADIUS = 20
    const val LABEL_TEXT_SIZE = 20

    val NAVIGATION_ICONS_LIST = listOf(
        R.drawable.nav_home_active,
        R.drawable.nav_stats_active,
        R.drawable.nav_menu_active,
        R.drawable.nav_settings_active,
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