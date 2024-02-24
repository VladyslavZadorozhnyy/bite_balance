package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.InstructionModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.bitebalance.common.NavigationAction
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.databinding.FragmentSettingsScreenBinding
import com.ui.basic.recycler_views.settings_recycler.SettingsRecyclerModel

class SettingsScreenFragment : BaseFragment<FragmentSettingsScreenBinding>() {

    override fun onStartFragment(): View {
        binding = FragmentSettingsScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayout)

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
        binding.settingsSublayout.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.settingRecycler.setBackgroundColor(themeVm.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.visibility = View.GONE
        toolbarBinding.forwardButton.visibility = View.GONE

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.settings),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
    }

    private fun setupRecycler() {
        binding.settingRecycler.setup(
            model = SettingsRecyclerModel(
                items = listOf(
                    InstructionModel(
                        iconRes = R.drawable.appearance_icon,
                        instructionText = requireContext().getString(R.string.appearance),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.translate_icon,
                        instructionText = requireContext().getString(R.string.language),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.measurement_icon,
                        instructionText = requireContext().getString(R.string.measurement),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.people_icon,
                        instructionText = requireContext().getString(R.string.about_us),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.instruction_icon,
                        instructionText = requireContext().getString(R.string.instruction),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.support_icon,
                        instructionText = requireContext().getString(R.string.support_us),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.feedback_icon,
                        instructionText = requireContext().getString(R.string.feedback),
                    ),
                ),
                primaryColor = themeVm.state.value!!.primaryColor,
                secondaryColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { processInstructionClicked(it) }
            )
        )
    }

    private fun processInstructionClicked(instruction: InstructionModel) {
        when (instruction.instructionText) {
            requireContext().getString(R.string.appearance) ->
                navigationVm.navigateTo(AppearanceScreenFragment(), NavigationAction.ADD)
            requireContext().getString(R.string.language) ->
                navigationVm.navigateTo(ChooseSettingScreenFragment.newInstance(), NavigationAction.ADD)
            requireContext().getString(R.string.measurement) ->
                navigationVm.navigateTo(ChooseSettingScreenFragment.newInstance(), NavigationAction.ADD)
            requireContext().getString(R.string.about_us) ->
                navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            requireContext().getString(R.string.instruction) ->
                navigationVm.navigateTo(TextScreenFragment(), NavigationAction.ADD)
            requireContext().getString(R.string.support_us) ->
                navigationVm.navigateTo(SupportFeedbackScreenFragment.newInstance(), NavigationAction.ADD)
            requireContext().getString(R.string.feedback) ->
                navigationVm.navigateTo(SupportFeedbackScreenFragment.newInstance(), NavigationAction.ADD)
        }
    }

    companion object {
        fun newInstance(): SettingsScreenFragment {
            return SettingsScreenFragment()
        }
    }
}