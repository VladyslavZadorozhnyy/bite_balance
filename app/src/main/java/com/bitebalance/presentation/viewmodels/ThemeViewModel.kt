package com.bitebalance.presentation.viewmodels

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitebalance.presentation.states.ThemeState


class ThemeViewModel(context: Context) : ViewModel() {
    private val mPrefsPrimaryColorKey = "SHARED_PREFERENCE_PRIMARY_COLOR"
    private val mPrefsSecondaryColorKey = "SHARED_PREFERENCE_SECONDARY_COLOR"
    private val mPrefsFontKey = "SHARED_PREFERENCE_FONT"

    private val mPrefsKey: String = "THEME_SHARED_PREFERENCE"
    private var mPrefs = context.getSharedPreferences(mPrefsKey, MODE_PRIVATE)

    var primaryColor: Int = 0
        set (value) {
            val initialSetting = field == 0
            field = value
            mPrefs.edit().putInt(mPrefsPrimaryColorKey, field).apply()
            if (!initialSetting) updateState()
        }
        get() {
            field = if (field == 0) mPrefs.getInt(mPrefsPrimaryColorKey, Color.BLACK) else field
            return field
        }

    var secondaryColor: Int = 0
        set (value) {
            val initialSetting = field == 0
            field = value
            mPrefs.edit().putInt(mPrefsSecondaryColorKey, field).apply()
            if (!initialSetting) updateState()
        }
        get() {
            field = if (field == 0) mPrefs.getInt(mPrefsSecondaryColorKey, Color.WHITE) else field
            return field
        }

    var fontResource: Int = 0
        set (value) {
            val initialSetting = field == 0
            field = value
            mPrefs.edit().putInt(mPrefsFontKey, field).apply()
            if (!initialSetting) updateState()
        }
        get() {
            field = if (field == 0) mPrefs.getInt(mPrefsFontKey, Color.WHITE) else field
            return field
        }

    private val _state by lazy { MutableLiveData(ThemeState(primaryColor, secondaryColor, fontResource)) }
    val state: LiveData<ThemeState> = _state

    private fun updateState() {
        _state.value = ThemeState(primaryColor, secondaryColor, fontResource)
    }
}