package com.ui.screens

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentAppearanceScreenBinding
import com.ui.mocks.MockGoalModel


class AppearanceScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentAppearanceScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeader()
        setupPrimaryColor()
        setupSecondaryColor()
        setupFontLayout()

        return binding.root
    }

    private fun setupHeader() {
        binding.sublayoutContainer.backgroundTintList =
            getColorStateList(requireContext(), R.color.black)

        binding.headline.setup(
            model = TextModel(
                textValue = "Appearance",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
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

    private fun setupPrimaryColor() {
        (binding.color1ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(5, getColorStateList(requireContext(), R.color.black))
            it.color = getColorStateList(requireContext(), R.color.black)
            binding.color1ColorView.background = it
        }

        binding.color1TextView.setup(
            model = TextModel(
                textValue = "Primary color",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupSecondaryColor() {
        (binding.color2ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(5, getColorStateList(requireContext(), R.color.black))
            it.color = getColorStateList(requireContext(), R.color.white)
            binding.color2ColorView.background = it
        }

        binding.color2TextView.setup(
            model = TextModel(
                textValue = "Secondary color",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupFontLayout() {
        binding.fontTextView.setup(
            model = TextModel(
                textValue = "Font",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }
}