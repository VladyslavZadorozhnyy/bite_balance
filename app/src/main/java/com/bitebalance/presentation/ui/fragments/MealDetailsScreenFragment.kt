package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bitebalance.databinding.FragmentMealDetailsScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MealMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockMetricModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MealDetailsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMealDetailsScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupSubtitles()
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
                textValue = "Dish #1",
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
                onClickListener = { navigationVm.popScreen() }
            )
        )
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModel(
                textValue = "Details:",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(MealMetricsModel())
    }
}