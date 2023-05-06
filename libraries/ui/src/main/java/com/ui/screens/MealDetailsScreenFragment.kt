package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentMealDetailsScreenBinding
import com.ui.mocks.MockMetricModel

class MealDetailsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMealDetailsScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeadline()
        setupSubtitles()
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeadline() {
        binding.headline.setup(
            model = TextModel(
                textValue = "Dish #1",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModel(
                textValue = "Details:",
                textSize = 15,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            MetricRecyclerModel(
                items = listOf(
                    MockMetricModel(
                        name = "Dish:",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        suffix = "in g",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        hint = "100",
                        suffix = "grams",
                        editable = false
                    )
                )
            )
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.back,
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {}
            )
        )
    }
}