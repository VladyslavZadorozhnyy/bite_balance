package com.bitebalance.unit.junit4

import android.graphics.Color
import android.view.Gravity
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.nav_bar.NavigationBarModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.recycler_views.instruction_recycler.InstructionRecyclerModel
import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

@RunWith(Parameterized::class)
class ParameterizedUnitTest(
    private val modelName: String,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun parameters(): Collection<String> {
            return listOf("CheckBoxModel", "InputFormModel", "NavigationBarModel",
                "DishRecyclerModel", "InstructionRecyclerModel", "MealRecyclerModel")
        }
    }

    @Test
    fun test_model_name_parameter() {
        when (modelName) {
            CheckBoxModel::class.simpleName -> CheckBoxModel(
                checked = false,
                active = false,
                onChecked = {},
                onUnchecked = {},
                backgroundColor = Color.WHITE,
                foregroundColor = Color.BLACK,
            )
            InputFormModel::class.simpleName -> InputFormModel(
                active = false,
                hint = "hint text",
                foregroundColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                hintGravity = Gravity.CENTER,
                onInputChange = { println("onInputChange") },
            )
            NavigationBarModel::class.simpleName -> NavigationBarModel(
                navIcons = listOf(),
                foregroundColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                onItemSelected = {},
            )
            DishRecyclerModel::class.simpleName -> DishRecyclerModel(
                items = listOf(),
                primaryColor = Color.WHITE,
                secondaryColor = Color.BLACK,
                onClickListener = {},
            )
            InstructionRecyclerModel::class.simpleName -> InstructionRecyclerModel(
                items = listOf(),
                foregroundColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
            MealRecyclerModel::class.simpleName -> MealRecyclerModel(
                items = listOf(),
                backgroundColor = Color.WHITE,
                foregroundColor = Color.BLACK,
                onClickListener = {},
                onSwipeListener = {},
            )
            else -> fail("unexpected case")
        }
    }
}