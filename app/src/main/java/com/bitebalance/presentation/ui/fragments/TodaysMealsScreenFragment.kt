package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentTodaysMealsScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockDishModel
import com.ui.mocks.MockMealModel
import com.ui.mocks.MockNutritionModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodaysMealsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentTodaysMealsScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

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
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.black)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Today's meals",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupRecycler() {
        binding.mealRecycler.setup(
            model = MealRecyclerModel(
                items = listOf(
                    MockMealModel(
                        mealTime = "08:30",
                        dish = MockDishModel(
                            name = "dish 1",
                            iconRes = R.drawable.dinner_icon,
                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                        ),
                        amount = 100
                    ),
                    MockMealModel(
                        mealTime = "10:30",
                        dish = MockDishModel(
                            name = "dish 2",
                            iconRes = R.drawable.dinner_icon,
                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                        ),
                        amount = 100
                    ),
                    MockMealModel(
                        mealTime = "12:30",
                        dish = MockDishModel(
                            name = "dish 3",
                            iconRes = R.drawable.dinner_icon,
                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                        ),
                        amount = 100
                    ),
                    MockMealModel(
                        mealTime = "14:30",
                        dish = MockDishModel(
                            name = "dish 4",
                            iconRes = R.drawable.dinner_icon,
                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                        ),
                        amount = 100
                    ),
                    MockMealModel(
                        mealTime = "18:30",
                        dish = MockDishModel(
                            name = "dish 5",
                            iconRes = R.drawable.dinner_icon,
                            nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                        ),
                        amount = 100
                    ),
                ),
                backgroundColorRes = R.color.black,
                onClickListener = { processMealClick(it) }
            )
        )
    }

    private fun processMealClick(meal: MockMealModel) {
        navigationVm.navigateTo(MealDetailsScreenFragment(), NavigationAction.ADD)
    }
}