package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.InstructionModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.databinding.FragmentInfoScreenBinding
import com.ui.basic.recycler_views.instruction_recycler.InstructionRecyclerModel

class InfoFragment : BaseFragment<FragmentInfoScreenBinding>() {

    override fun onStartFragment(): View {
        binding = FragmentInfoScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

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
        binding.root.setBackgroundColor(secondaryColor)
        binding.lineView.setBackgroundColor(secondaryColor)
        binding.sublayoutContainerConstraint.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.forwardButton.visibility = View.INVISIBLE

        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.icons_legend),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupRecycler() {
        binding.iconsLegends.setup(
            model = InstructionRecyclerModel(
                backgroundColor = primaryColor,
                foregroundColor = secondaryColor,
                items = listOf(
                    InstructionModel(
                        iconRes = R.drawable.info_icon,
                        instructionText = getString(R.string.get_info_leg),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.meals_icon,
                        instructionText = getString(R.string.list_today_meal_leg),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.reset_icon,
                        instructionText = getString(R.string.reset_today_meal_leg),
                    ),
                    InstructionModel(
                        iconRes = R.drawable.add_icon,
                        instructionText = getString(R.string.add_meal_leg),
                    ),
                ),
            ),
        )
    }

    companion object {
        fun newInstance(): InfoFragment {
            return InfoFragment()
        }
    }
}