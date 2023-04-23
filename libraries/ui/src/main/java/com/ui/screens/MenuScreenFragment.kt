package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentMenuScreenBinding
import com.ui.mocks.MockDishModel
import com.ui.mocks.MockNutritionModel

class MenuScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMenuScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeadline()
        setupDishRecycler()

        binding.textIconButtonSample.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 80,
                strokeWidth = 5,
                labelTextSize = 14,
                labelTextRes = R.string.add_new,
                foregroundColorRes = R.color.black,
                backgroundColorRes = R.color.white,
                onClickListener =  {
                    Toast.makeText(activity,"TextIconButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )

        return binding.root
    }

    private fun setupHeadline() {
        binding.headline.setup(
            model = TextModel(
                textValue = "Menu",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupDishRecycler() {
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
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.breakfast_icon,
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
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.breakfast_icon,
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
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.breakfast_icon,
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
                        iconRes = R.drawable.breakfast_icon,
                        nutritionVal = MockNutritionModel(0F, 0F, 0F, 0F)
                    ),
                    MockDishModel(
                        name = "Dish 3",
                        iconRes = R.drawable.breakfast_icon,
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