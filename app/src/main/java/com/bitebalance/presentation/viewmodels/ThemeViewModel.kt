package com.bitebalance.presentation.viewmodels

import android.graphics.Color
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import android.content.Context.MODE_PRIVATE
import com.bitebalance.common.Constants.PREF_FONT
import com.bitebalance.common.Constants.PREF_KEY
import com.bitebalance.presentation.states.ThemeState
import com.bitebalance.common.Constants.PREF_SEC_COLOR
import com.bitebalance.common.Constants.PREF_PRIM_COLOR


class ThemeViewModel(context: Context) : ViewModel() {
    private var mPrefs = context.getSharedPreferences(PREF_KEY, MODE_PRIVATE)

    private var primaryColor: Int = 0
    private var secondaryColor: Int = 0

    var fontResource: Int = 0
        set (value) {
            val initialSetting = field == 0
            field = value
            mPrefs.edit().putInt(PREF_FONT, field).apply()
            if (!initialSetting) updateState()
        }
        get() {
            field = if (field == 0) mPrefs.getInt(PREF_FONT, Color.WHITE) else field
            return field
        }

    private val _state by lazy { MutableLiveData(ThemeState(getPrimaryColor(), getSecondaryColor(), fontResource)) }
    val state: LiveData<ThemeState> = _state

    private fun getPrimaryColor(): Int {
        primaryColor = if (primaryColor == 0)
            mPrefs.getInt(PREF_PRIM_COLOR, Color.BLACK) else primaryColor
        return primaryColor
    }

    fun setPrimaryColor(primaryColorValue: Int) {
        val initialSetting = primaryColor == 0
        primaryColor = primaryColorValue
        mPrefs.edit().putInt(PREF_PRIM_COLOR, primaryColor).apply()
        if (!initialSetting) updateState()
    }

    private fun getSecondaryColor(): Int {
        secondaryColor = if (secondaryColor == 0)
            mPrefs.getInt(PREF_SEC_COLOR, Color.WHITE) else secondaryColor
        return secondaryColor
    }

    fun setSecondaryColor(secondaryColorValue: Int) {
        val initialSetting = secondaryColor == 0
        secondaryColor = secondaryColorValue
        mPrefs.edit().putInt(PREF_SEC_COLOR, secondaryColor).apply()
        if (!initialSetting) updateState()
    }

    private fun updateState() {
        _state.value = ThemeState(primaryColor, secondaryColor, fontResource)
    }
}