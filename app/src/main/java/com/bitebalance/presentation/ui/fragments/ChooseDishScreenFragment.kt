package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.databinding.FragmentChooseDishScreenBinding
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockDishModel
import com.ui.mocks.MockNutritionModel


class ChooseDishScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentChooseDishScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Choose dish",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )
    }

    private fun setupRecycler() {
        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = listOf(
                    MockDishModel(
                        name = "Dish 1",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 2",
                        iconRes = R.drawable.lunch_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.dinner_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 4",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 5",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 6",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 1",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 2",
                        iconRes = R.drawable.lunch_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.dinner_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 4",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 5",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 6",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 1",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 2",
                        iconRes = R.drawable.lunch_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.dinner_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 4",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 5",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 6",
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    )
                )
            )
        )
    }
}