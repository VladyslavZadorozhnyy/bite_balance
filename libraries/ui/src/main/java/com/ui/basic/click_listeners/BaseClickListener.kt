package com.ui.basic.click_listeners

import android.os.SystemClock

class BaseClickListener {
    companion object {
        private const val DOUBLE_TAP_PING = 1000
        private var previousClickTime = 0L

        fun processClick(listener: () -> Unit) {
            if (SystemClock.elapsedRealtime() - previousClickTime < DOUBLE_TAP_PING)
                return

            previousClickTime = SystemClock.elapsedRealtime()
            listener.invoke()
        }
    }
}