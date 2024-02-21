package com.bitebalance.presentation.ui.fragments

import android.util.Log
import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.basic.recycler_views.text_recycler.TextRecyclerModel
import com.bitebalance.databinding.FragmentChooseSettingScreenBinding

class ChooseSettingScreenFragment : BaseFragment<FragmentChooseSettingScreenBinding>() {
    override fun onStartFragment(): View {
        binding = FragmentChooseSettingScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.root)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupRecycler()
        }
    }

    override fun onStopFragment() {
        themeVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.textRecycler.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() },
            )
        )

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.choosings),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupRecycler() {
        binding.textRecycler.setup(
            model = TextRecyclerModel(
                items = listOf(
                    TextModel(
                        textValue = requireContext().getString(R.string.appearance),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.language),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.measurement),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.about_us),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.instruction),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.support_us),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                    TextModel(
                        textValue = requireContext().getString(R.string.feedback),
                        textSize = Constants.TEXT_SIZE_BIG,
                        textColor = themeVm.state.value!!.primaryColor,
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                    ),
                ),
                onClickListener = { processVariantClicked(it) }
            )
        )
    }

    private fun processVariantClicked(variant: TextModel) {
        Log.d("AAADIP", "variant: $variant")
    }

    companion object {
        fun newInstance(): ChooseSettingScreenFragment {
            return ChooseSettingScreenFragment()
        }
    }
}