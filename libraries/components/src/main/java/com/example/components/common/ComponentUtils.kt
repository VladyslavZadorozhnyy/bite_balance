package com.example.components.common

import android.content.Context

object ComponentUtils {
    fun dpToPx(context: Context, dpValue: Int): Int {
        return (dpValue * context.resources.displayMetrics.density).toInt()
    }

    fun pxToDp(context: Context, pxValue: Int): Int {
        return (pxValue / context.resources.displayMetrics.density).toInt()
    }
}