package com.ui.common

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object ComponentUiUtils {
    fun dpToPx(context: Context, dpValue: Int): Int {
        return (dpValue * context.resources.displayMetrics.density).toInt()
    }

    fun pxToDp(context: Context, pxValue: Int): Int {
        return (pxValue / context.resources.displayMetrics.density).toInt()
    }

    fun hideKeyBoard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus?.let { imm.hideSoftInputFromWindow(it.windowToken, 0) }
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
    Graph,
    Recycler
}