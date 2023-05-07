package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentSupportFeedbackScreenBinding
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog

class SupportFeedbackScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentSupportFeedbackScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeader()
        setupSubtexts()
        setupButton()

        return binding.root
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Support Us",
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
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )
    }

    private fun setupSubtexts() {
        binding.subtext.backgroundTintList =
            getColorStateList(requireContext(), R.color.black)

        binding.donationPlanText.backgroundTintList =
            getColorStateList(requireContext(), R.color.black)

        binding.subtext.setup(
            model = TextModel(
                textValue = "*Donation text*",
                textSize = 20,
                textColorRes = R.color.white,
                backgroundColor = R.color.transparent
            )
        )

        binding.inputSubtext.setup(
            model = InputFormModel(
                active = true,
                hint = "*Message to developers*",
                onInputChange = {  }
            )
        )

        binding.donationPlanText.setup(
            model = TextModel(
                textValue = "*Donation plan*",
                textSize = 20,
                textColorRes = R.color.white,
                backgroundColor = R.color.transparent
            )
        )
    }

    private fun setupButton() {
        binding.commitButton.setup(
            ButtonModel(
                labelTextRes = R.string.commit,
                labelTextSize = 15,
                iconRes = R.drawable.donation_icon,
                iconSize = 80,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    ConfirmDialog(
                        activity = requireActivity(),
                        model = BaseDialogModel(
                            backgroundColorRes = R.color.white,
                            textColorRes = R.color.black,
                            title = "Donated successfully!"
                        )
                    ).show()
                }
            )
        )
    }
}