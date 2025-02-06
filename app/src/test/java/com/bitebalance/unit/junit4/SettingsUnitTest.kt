package com.bitebalance.unit.junit4

import android.graphics.Color
import com.ui.basic.recycler_views.settings_recycler.SettingsRecyclerModel
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(SettingsTests::class)
class SettingsUnitTest {
    @Test
    fun settings_recycler_model_test() {
        println("settings_recycler_model_test")
        SettingsRecyclerModel(
            items = listOf(),
            primaryColor = Color.WHITE,
            secondaryColor = Color.BLACK,
            onClickListener = {},
        )
    }
}