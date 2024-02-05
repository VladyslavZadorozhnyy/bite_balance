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