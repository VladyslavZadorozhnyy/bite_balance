package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentStatsScreenBinding
import com.ui.components.graph.component.GraphModel
import com.ui.mocks.MockNutritionModel

class StatsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentStatsScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupButtons()
        setupChart()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.questionButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.question_mark_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.goalButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.goal_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.headline.setup(
            model = TextModel(
                textValue = "Statistics",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupButtons() {
        binding.backwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.forwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )
    }

    private fun setupChart() {
        binding.monthTextview.setup(
            model = TextModel(
                textValue = "January 2023",
                textSize = 25,
                textColorRes = R.color.white,
                backgroundColor = R.color.black,
            )
        )

        binding.chart.setup(
            GraphModel(
                consumption = listOf(
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        2000F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1900F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1800F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1700F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1600F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1500F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1900F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1900F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1900F
                    ),
                    MockNutritionModel(
                        10F,
                        20F,
                        15F,
                        1900F
                    )
                ),
                consumptionGoal = MockNutritionModel(
                    10F,
                    40F,
                    8F,
                    1500F
                ),
                screenSpan = 7
            )
        )
    }
}