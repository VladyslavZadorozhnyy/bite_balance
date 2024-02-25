package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.bitebalance.databinding.FragmentSupportFeedbackScreenBinding

class SupportFeedbackFragment : BaseFragment<FragmentSupportFeedbackScreenBinding>() {

    override fun onStartFragment(): View {
        binding = FragmentSupportFeedbackScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupSubtexts()
            setupButton()
        }
    }

    override fun onStopFragment() {
        themeVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.support_us),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupSubtexts() {
        binding.subtext.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.donationPlanText.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)

        binding.subtext.setup(
            model = TextModel(
                textValue = getString(R.string.donation_text),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.inputSubtext.setup(
            model = InputFormModel(
                active = true,
                hint = getString(R.string.message_to_developers),
                onInputChange = {  },
                backgroundColor = themeVm.state.value!!.secondaryColor,
                foregroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.donationPlanText.setup(
            model = TextModel(
                textValue = getString(R.string.donation_plan),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
    }

    private fun setupButton() {
        binding.commitButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.commit,
                labelTextSize = Constants.TEXT_SIZE_SMALL,
                iconRes = R.drawable.donation_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = {
                    ConfirmDialog(
                        activity = requireActivity(),
                        model = BaseDialogModel(
                            backgroundColor = themeVm.state.value!!.primaryColor,
                            textColor = themeVm.state.value!!.secondaryColor,
                            title = getString(R.string.donated),
                            buttonText = R.string.done,
                        ),
                    ).show()
                },
            ),
        )
    }

    companion object {
        fun newInstance(): SupportFeedbackFragment {
            return SupportFeedbackFragment()
        }
    }
}