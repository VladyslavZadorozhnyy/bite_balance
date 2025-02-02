package com.bitebalance.unit.junit4

import android.graphics.Color
import com.ui.basic.buttons.common.ButtonModel
import com.ui.common.Constants
import org.junit.Assert.*
import org.junit.Test

class UiTest {
    @Test
    fun basic_buttons_common_button_model_test() {
        var buttonModel = ButtonModel()

        assertNull(buttonModel.iconRes)
        assertNotNull(buttonModel.iconSize)

        assertEquals(Constants.ICON_SIZE, buttonModel.iconSize)
        assertNotNull(buttonModel.iconSize)

        assertEquals(null, buttonModel.labelTextRes)
        assertNull(buttonModel.labelTextRes)

        assertEquals(Constants.TEXT_SIZE, buttonModel.labelTextSize)
        assertNotNull(buttonModel.labelTextSize)

        assertEquals(0, buttonModel.strokeWidth)
        assertNotNull(buttonModel.strokeWidth)

        assertEquals(Color.WHITE, buttonModel.foregroundColor)
        assertNotNull(buttonModel.foregroundColor)

        assertEquals(Color.BLACK, buttonModel.backgroundColor)
        assertNotNull(buttonModel.backgroundColor)

        assertEquals(null, buttonModel.onClickListener)
        assertNull(buttonModel.onClickListener)

        assertTrue(buttonModel.isClickable)
        assertNotNull(buttonModel.isClickable)
    }
}