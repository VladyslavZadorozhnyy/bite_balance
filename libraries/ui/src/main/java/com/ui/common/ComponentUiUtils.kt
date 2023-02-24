package com.ui.common

import android.content.Context

object ComponentUiUtils {
    fun dpToPx(context: Context, dpValue: Int): Int {
        return (dpValue * context.resources.displayMetrics.density).toInt()
    }

    fun pxToDp(context: Context, pxValue: Int): Int {
        return (pxValue / context.resources.displayMetrics.density).toInt()
    }
}

object ComponentUiConstants {
    const val TEXT_STROKE_WIDTH = 3F
    const val TEXT_SIZE = 20
    const val CAROUSEL_SIZE = 4
}

enum class ComponentUiType {
    Button,
    Text,
    Indicator,
    Carousel,
    NavBar,
    Checkbox,
    Input,
    Dialog,
    Graph
}