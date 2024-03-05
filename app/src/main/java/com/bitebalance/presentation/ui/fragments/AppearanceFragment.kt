package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import android.graphics.Color
import com.ui.common.Constants
import yuku.ambilwarna.AmbilWarnaDialog
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import android.graphics.drawable.GradientDrawable
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.databinding.FragmentAppearanceScreenBinding


class AppearanceFragment : BaseFragment<FragmentAppearanceScreenBinding>() {
    override fun onStartFragment(): View {
        binding = FragmentAppearanceScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.root)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupPrimaryColor()
            setupSecondaryColor()
            setupFontLayout()
        }
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.appearance),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { activity?.onBackPressed() },
            ),
        )
    }

    private fun setupPrimaryColor() {
        (binding.color1ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(Constants.COLOR_ICON_STROKE_WIDTH, ColorStateList.valueOf(Color.BLACK))
            it.color = ColorStateList.valueOf(primaryColor)
            binding.color1ColorView.background = it
            binding.color1Bar.setBackgroundColor(primaryColor)
            binding.color1.setBackgroundColor(secondaryColor)
        }
        binding.color1TextView.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.primary_color),
                textSize = Constants.TEXT_SIZE,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        binding.color1.setOnClickListener {
            setupColorPickingDialog(
                changePrimaryColor = true,
                changeSecondaryColor = false,
            )
        }
    }

    private fun setupSecondaryColor() {
        (binding.color2ColorView.background as? GradientDrawable)?.let {
            it.mutate()
            it.setStroke(Constants.COLOR_ICON_STROKE_WIDTH, ColorStateList.valueOf(Color.BLACK))
            it.color = ColorStateList.valueOf(secondaryColor)
            binding.color2ColorView.background = it
            binding.color2Bar.setBackgroundColor(primaryColor)
            binding.color2.setBackgroundColor(secondaryColor)
        }
        binding.color2TextView.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.secondary_color),
                textSize = Constants.TEXT_SIZE,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        binding.color2.setOnClickListener {
            setupColorPickingDialog(
                changePrimaryColor = false,
                changeSecondaryColor = true,
            )
        }
    }

    private fun setupFontLayout() {
        binding.fontTextView.setup(
            model = TextModel(
                textValue = getString(R.string.font),
                textSize = Constants.TEXT_SIZE,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        binding.fontLayout.setBackgroundColor(secondaryColor)
        binding.fontBar.setBackgroundColor(primaryColor)
        binding.fontIcon.setColorFilter(primaryColor)
    }

    private fun setupColorPickingDialog(
        changePrimaryColor: Boolean = false,
        changeSecondaryColor: Boolean = false,
    ) {
        val dialogTitleRes = if (changePrimaryColor) getString(R.string.choose_primary_color)
        else if (changeSecondaryColor) getString(R.string.choose_secondary_color)
        else requireContext().getString(R.string.empty_line)

        val defaultColor: Int = if (changePrimaryColor) themeVm.state.value?.primaryColor ?: -1
        else themeVm.state.value?.secondaryColor ?: -1

        AmbilWarnaDialog(requireContext(), defaultColor, object : AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {}

            override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                if (changePrimaryColor) themeVm.setPrimaryColor(color)
                else if (changeSecondaryColor) themeVm.setSecondaryColor(color)

                mActivity.updateNavBarColors()
            }
        }).apply {
            this.dialog.setTitle(dialogTitleRes)
            this.show()
        }
    }

    companion object {
        fun newInstance(): AppearanceFragment {
            return AppearanceFragment()
        }
    }
}