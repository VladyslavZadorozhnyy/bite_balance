package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentAppearanceScreenBinding
import com.bitebalance.presentation.ui.activites.MainActivity
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import yuku.ambilwarna.AmbilWarnaDialog


class AppearanceScreenFragment : Fragment() {
    private val binding by lazy { FragmentAppearanceScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupViewModelsObservation() {
        themeViewModel.state.observe(this) {
            setupHeader()
            setupStyling()
            setupPrimaryColor()
            setupSecondaryColor()
            setupFontLayout()
        }
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Appearance",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { activity?.onBackPressed() }
            )
        )
    }

    private fun setupPrimaryColor() {
        (binding.color1ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(5, ColorStateList.valueOf(Color.BLACK))
            it.color = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
            binding.color1ColorView.background = it
            binding.color1Bar.setBackgroundColor(themeViewModel.state.value!!.primaryColor)
            binding.color1.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        }

        binding.color1TextView.setup(
            model = TextModelNew(
                textValue = "Primary color",
                textSize = 25,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )

        binding.color1.setOnClickListener {
            setupColorPickingDialog(changePrimaryColor = true, changeSecondaryColor = false)
        }
    }

    private fun setupSecondaryColor() {
        (binding.color2ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(5, ColorStateList.valueOf(Color.BLACK))
            it.color = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
            binding.color2ColorView.background = it
            binding.color2Bar.setBackgroundColor(themeViewModel.state.value!!.primaryColor)
            binding.color2.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        }

        binding.color2TextView.setup(
            model = TextModelNew(
                textValue = "Secondary color",
                textSize = 25,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )

        binding.color2.setOnClickListener {
            setupColorPickingDialog(changePrimaryColor = false, changeSecondaryColor = true)
        }
    }

    private fun setupFontLayout() {
        binding.fontTextView.setup(
            model = TextModelNew(
                textValue = "Font",
                textSize = 25,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
        binding.fontLayout.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.fontBar.setBackgroundColor(themeViewModel.state.value!!.primaryColor)
        binding.fontIcon.setColorFilter(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupColorPickingDialog(
        changePrimaryColor: Boolean = false,
        changeSecondaryColor: Boolean = false,
    ) {
        val dialogTitle = if (changePrimaryColor)
            "Choose primary color"
        else if (changeSecondaryColor)
            "Choose secondary color"
        else
            ""

        val defaultColor: Int = if (changePrimaryColor)
            themeViewModel.state.value?.primaryColor ?: -1
        else
            themeViewModel.state.value?.secondaryColor ?: -1

        AmbilWarnaDialog(requireContext(), defaultColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {}

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                if (changePrimaryColor)
                    themeViewModel.primaryColor = color
                else if (changeSecondaryColor)
                    themeViewModel.secondaryColor = color

                (activity as? MainActivity)?.updateNavBarColors()
            }
        }).apply {
            this.dialog.setTitle(dialogTitle)
            this.show()
        }
    }
}