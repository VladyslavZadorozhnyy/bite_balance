package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentSettingsScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.recycler_views.settings_recycler.SettingsRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockInstructionModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingsScreenFragment : Fragment() {

    private val binding by lazy {
        FragmentSettingsScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.settingsSublayout.backgroundTintList = getColorStateList(requireContext(), R.color.black)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Settings",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupRecycler() {
        binding.settingRecycler.setup(
            model = SettingsRecyclerModel(
                items = listOf(
                    MockInstructionModel(
                        iconRes = R.drawable.appearance_icon,
                        instructionText = "Appearance"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.translate_icon,
                        instructionText = "Language"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.measurement_icon,
                        instructionText = "Measurement"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.people_icon,
                        instructionText = "About Us"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.instruction_icon,
                        instructionText = "Instruction"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.support_icon,
                        instructionText = "Support Us"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.feedback_icon,
                        instructionText = "Feedback"
                    ),
                ),
                onClickListener = { processInstructionClicked(it) }
            )
        )
    }

    private fun processInstructionClicked(instruction: MockInstructionModel) {
        when (instruction.instructionText) {
            "Appearance" -> navigationVm.navigateTo(AppearanceScreenFragment(), NavigationAction.ADD)
            "Language" -> navigationVm.navigateTo(ChooseSettingScreenFragment(), NavigationAction.ADD)
            "Measurement" -> navigationVm.navigateTo(ChooseSettingScreenFragment(), NavigationAction.ADD)
            "About Us" -> navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            "Instruction" -> navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            "Support Us" -> navigationVm.navigateTo(SupportFeedbackScreenFragment(), NavigationAction.ADD)
            "Feedback" -> navigationVm.navigateTo(SupportFeedbackScreenFragment(), NavigationAction.ADD)
            else -> Log.d("AAADIP", "HERE")
        }
    }
}