package com.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentHomeScreenBinding
import com.ui.components.progress.carousel.ProgressCarouselModel
import com.ui.mocks.MockNutritionModel

class HomeScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentHomeScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupGreetings()
        setupCarousel()
        setupButtons()

        return binding.root
    }

    private fun setupGreetings() {
        binding.greetings.setup(
            model = TextModel(
                textValue = "Good morning",
                textSize = 35,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )
    }

    private fun setupCarousel() {
        binding.progressCarousel.setup(
            model = ProgressCarouselModel(
                consumed = MockNutritionModel(
                    fat = 10F,
                    carb = 14F,
                    kcal = 750F,
                    protein = 10F,
                ),
                goalConsumption = MockNutritionModel(
                    fat = 10F,
                    carb = 12F,
                    kcal = 2000F,
                    protein = 10F,
                )
            )
        )
    }

    private fun setupButtons() {
        binding.infoButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"InfoButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )

        binding.todayMealsButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.meals_icon,
                iconSize = 80,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"TodayMealsButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )

        binding.resetProgressButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.reset_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"ResetProgressButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )

        binding.addMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"AddMealButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }
}