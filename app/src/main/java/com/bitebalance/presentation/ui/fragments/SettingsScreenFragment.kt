package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentSettingsScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.recycler_views.settings_recycler.SettingsRecyclerModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.model.InstructionModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SettingsScreenFragment : Fragment() {
    private val binding by lazy { FragmentSettingsScreenBinding.inflate(layoutInflater) }

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
        themeViewModel.state.observe(this) {
            setupStyling()
            setupHeader()
            setupRecycler()
        }
    }

    private fun setupStyling() {
        binding.settingsSublayout.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
        binding.settingRecycler.setBackgroundColor(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Settings",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupRecycler() {
        binding.settingRecycler.setup(
            model = SettingsRecyclerModel(
                items = listOf(
                    InstructionModel(
                        iconRes = R.drawable.appearance_icon,
                        instructionText = "Appearance"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.translate_icon,
                        instructionText = "Language"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.measurement_icon,
                        instructionText = "Measurement"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.people_icon,
                        instructionText = "About Us"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.instruction_icon,
                        instructionText = "Instruction"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.support_icon,
                        instructionText = "Support Us"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.feedback_icon,
                        instructionText = "Feedback"
                    ),
                ),
                primaryColor = themeViewModel.state.value!!.primaryColor,
                secondaryColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { processInstructionClicked(it) }
            )
        )
    }

    private fun processInstructionClicked(instruction: InstructionModel) {
        when (instruction.instructionText) {
            "Appearance" -> navigationVm.navigateTo(AppearanceScreenFragment(), NavigationAction.ADD)
            "Language" -> navigationVm.navigateTo(ChooseSettingScreenFragment(), NavigationAction.ADD)
            "Measurement" -> navigationVm.navigateTo(ChooseSettingScreenFragment(), NavigationAction.ADD)
            "About Us" -> navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            "Instruction" -> navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            "Support Us" -> navigationVm.navigateTo(SupportFeedbackScreenFragment(), NavigationAction.ADD)
            "Feedback" -> navigationVm.navigateTo(SupportFeedbackScreenFragment(), NavigationAction.ADD)
        }
    }
}