package com.bitebalance.unit.junit4

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.ui.basic.texts.common.TextModel
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiUtils
import com.ui.common.Constants
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.experimental.theories.DataPoint
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith
import java.util.*

@RunWith(Theories::class)
class TheoryUnitTest {

    @Theory
    fun test_component(componentName: String) {
        when (componentName) {
            Constants::class.simpleName -> {
                Constants.GREEN_COLOR_RANGE
                Constants.YELLOW_COLOR_RANGE
                Constants.NAVIGATION_ICONS_LIST
                Constants.SPINNER_ITEMS
                Constants.DATE_FORMAT
            }
            TextModel::class.simpleName -> {
                TextModel(
                    textValue = "",
                    textSize = Constants.TEXT_SIZE,
                    textColor = Color.WHITE,
                    backgroundColor = Color.BLACK,
                    backgroundRes = null,
                    backgroundResDrawable = null,
                    isSingleLine = false,
                )
            }
            BaseUiComponentModel::class.simpleName -> {
                BaseUiComponentModel(
                    componentType = Constants.ComponentUiType.Button
                )
            }
            BaseDialogModel::class.simpleName -> {
                BaseDialogModel(
                    title = "title",
                    backgroundColor = Color.BLACK,
                    textColor = Color.WHITE,
                    buttonText = R.string.done,
                    onConfirmClicked = { println("onConfirmClicked") },
                    onPositiveClicked = { println("onPositiveClicked") },
                    onNegativeClicked = { println("onNegativeClicked") },
                    onInputConfirmed = { println("onInputConfirmed") },
                )
            }
            ComponentUiUtils::class.simpleName -> ComponentUiUtils
            else -> fail("Unchecked case")
        }
    }

    companion object {
        @JvmStatic
        val textModelName = "TextModel"
        @DataPoint get

        @JvmStatic
        val commonConstantsName = "Constants"
        @DataPoint get

        @JvmStatic
        val otherCommonNames = listOf("ComponentUiUtils", "BaseUiComponentModel", "BaseDialogModel")
        @DataPoints get
    }
}