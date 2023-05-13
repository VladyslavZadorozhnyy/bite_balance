package com.bitebalance.presentation.ui.fragments

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentAppearanceScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AppearanceScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentAppearanceScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupPrimaryColor()
        setupSecondaryColor()
        setupFontLayout()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.black)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Appearance",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
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