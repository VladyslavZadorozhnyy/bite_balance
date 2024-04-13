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

class SettingsFragment : BaseFragment<FragmentSettingsScreenBinding>() {

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

    private fun setupStyling() {
        binding.settingsSublayout.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(secondaryColor)
        binding.settingRecycler.setBackgroundColor(primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.visibility = View.GONE
        toolbarBinding.forwardButton.visibility = View.GONE

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.settings),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
    }

    private fun setupRecycler() {
        binding.settingRecycler.setup(
            model = SettingsRecyclerModel(
                items = listOf(
                    InstructionModel(
                        iconRes = R.drawable.appearance_icon,
                        instructionText = getString(R.string.appearance),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.measurement_icon,
                        instructionText = getString(R.string.measurement),
                    ),
                ),
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onClickListener = { processInstructionClicked(it) },
            ),
        )
    }

    private fun processInstructionClicked(instruction: InstructionModel) {
        when (instruction.instructionText) {
            getString(R.string.appearance) ->
                navigationVm.navigateTo(AppearanceFragment.newInstance(), NavigationAction.ADD)
            getString(R.string.measurement) ->
                navigationVm.navigateTo(MeasurementFragment.newInstance(), NavigationAction.ADD)
        }
    }

    companion object {
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }
}