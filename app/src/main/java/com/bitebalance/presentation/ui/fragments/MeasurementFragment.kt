package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.input_dialog.InputDialog
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.recycler_views.text_recycler.TextRecyclerModel
import com.bitebalance.databinding.FragmentChooseSettingScreenBinding

class MeasurementFragment : BaseFragment<FragmentChooseSettingScreenBinding>() {
    private val nutritionVm by sharedViewModel<NutritionViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentChooseSettingScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.root)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
        }
        nutritionVm.state.observe(this) { state ->
            if (state.isLoading) return@observe

            if (state.data == null && state.message.isNotEmpty()) {
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        title = state.message,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                        buttonText = R.string.ok,
                    ),
                ).show()

                if (state.isSuccessful) nutritionVm.getConsumedGoalValues()
                else nutritionVm.resetState()
            } else if (state.data == null) {
                nutritionVm.getConsumedGoalValues()
            } else {
                setupRecycler(state.data[0])
            }
        }
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.textRecycler.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.measurement),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
    }

    private fun setupRecycler(ntrModel: NutritionValueModel) {
        binding.textRecycler.setup(
            model = TextRecyclerModel(
                items = listOf(
                    TextModel(
                        textValue = getString(R.string.kcals_m, ntrModel.kcals),
                        textSize = Constants.TEXT_SIZE,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                    ),
                    TextModel(
                        textValue = getString(R.string.prots_m, ntrModel.prots),
                        textSize = Constants.TEXT_SIZE,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                    ),
                    TextModel(
                        textValue = getString(R.string.fats_m, ntrModel.fats),
                        textSize = Constants.TEXT_SIZE,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                    ),
                    TextModel(
                        textValue = getString(R.string.carbs_m, ntrModel.carbs),
                        textSize = Constants.TEXT_SIZE,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                    ),
                ),
                onClickListener = { processVariantClicked(it) },
            ),
        )
    }

    private fun processVariantClicked(variant: TextModel) {
        var componentName = ""

        if (variant.textValue.contains(getString(R.string.kcals_g)))
            componentName = getString(R.string.kcals_g)

        if (variant.textValue.contains(getString(R.string.prots_g)))
            componentName = getString(R.string.prots_g)

        if (variant.textValue.contains(getString(R.string.fats_g)))
            componentName = getString(R.string.fats_g)

        if (variant.textValue.contains(getString(R.string.carbs_g)))
            componentName = getString(R.string.carbs_g)

        InputDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = secondaryColor,
                textColor = primaryColor,
                title = getString(R.string.update_component, componentName),
                onInputConfirmed = { nutritionVm.updateConsumedGoalValue(componentName, it) },
                buttonText = R.string.update,
            ),
        ).show()
    }

    companion object {
        fun newInstance(): MeasurementFragment {
            return MeasurementFragment()
        }
    }
}