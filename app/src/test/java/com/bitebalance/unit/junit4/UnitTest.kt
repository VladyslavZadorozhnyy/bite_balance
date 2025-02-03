package com.bitebalance.unit.junit4

import android.content.Context
import android.graphics.Color
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class UiTest {
    companion object {
        @Mock
        private var mockContext: Context? = null

        @BeforeClass
        @JvmStatic
        fun initContext() {
            mockContext = mock(Context::class.java)
        }

        @AfterClass
        @JvmStatic
        fun removeContext() {
            println("mockContext = null, AAADIP")
            mockContext = null
        }
    }

    @Test
    fun basic_buttons_common_button_model_test() {
        val buttonModel = ButtonModel()

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

    @Test
    fun basic_buttons_common_button_model_matcher_test() {
        val buttonModel1 = ButtonModel()
        val buttonModel2 = ButtonModel(
            iconRes = null,
            iconSize = Constants.DISH_ICON_SIZE,
            foregroundColor = Color.BLACK,
            backgroundColor = Color.WHITE,
            onClickListener = {},
        )

        try {
            assertTrue(buttonModel1 is BaseUiComponentModel)
        } catch (e: java.lang.Exception) {
            fail("buttonModel1 !is BaseUiComponentModel")
        }

        try {
            buttonModel1 as IconButton
            fail("buttonModel1 as IconButton")
        } catch (e: java.lang.Exception) {}

        assertThat(null, allOf(equalTo(buttonModel1.iconRes), equalTo(buttonModel2.iconRes)))
        assertThat(Color.WHITE, not(allOf(equalTo(buttonModel1.foregroundColor), equalTo(buttonModel2.foregroundColor))))
        assertThat(Color.BLACK, anyOf(equalTo(buttonModel1.backgroundColor), equalTo(buttonModel2.backgroundColor)))
        assertThat(buttonModel1, sameInstance(buttonModel1))
        assertThat(buttonModel2, not(sameInstance(buttonModel1)))
    }

    @Test
    fun basic_buttons_common_icon_button_test() {
        val buttonModel = ButtonModel(
            iconRes = null,
            iconSize = Constants.ICON_SIZE_LARGE,
            foregroundColor = Color.BLACK,
            backgroundColor = Color.WHITE,
            onClickListener = {},
        )
        IconButton(mockContext)
    }
}