package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentSupportFeedbackScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SupportFeedbackScreenFragment : Fragment() {
    private val binding by lazy { FragmentSupportFeedbackScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    private fun setupViewModelsObservation() {
        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupSubtexts()
            setupButton()
        }
    }

    private fun setupStyling() {
        binding.root.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Support Us",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )
    }

    private fun setupSubtexts() {
        binding.subtext.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)

        binding.donationPlanText.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)

        binding.subtext.setup(
            model = TextModelNew(
                textValue = "*Donation text*",
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.inputSubtext.setup(
            model = InputFormModel(
                active = true,
                hint = "*Message to developers*",
                onInputChange = {  },
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.donationPlanText.setup(
            model = TextModelNew(
                textValue = "*Donation plan*",
                textSize = 20,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor
            )
        )
    }

    private fun setupButton() {
        Log.d("AAADIP", "setupButton() started")
        binding.commitButton.setup(
            ButtonModel(
                labelTextRes = R.string.commit,
                labelTextSize = 15,
                iconRes = R.drawable.donation_icon,
                iconSize = 80,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = {
                    ConfirmDialog(
                        activity = requireActivity(),
                        model = BaseDialogModel(
                            backgroundColor = themeViewModel.state.value!!.primaryColor,
                            textColor = themeViewModel.state.value!!.secondaryColor,
                            title = "Donated successfully!"
                        )
                    ).show()
                }
            )
        )
        Log.d("AAADIP", "setupButton() ended, 2")
    }
}