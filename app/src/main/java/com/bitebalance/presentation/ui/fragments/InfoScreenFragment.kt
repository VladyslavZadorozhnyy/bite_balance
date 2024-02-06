package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentInfoScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.instruction_recycler.InstructionRecyclerModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.model.InstructionModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class InfoScreenFragment : Fragment() {
    private val binding by lazy { FragmentInfoScreenBinding.inflate(layoutInflater) }

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
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)

        binding.sublayoutContainer.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.forwardButton.visibility = View.INVISIBLE

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Icons Legend",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }

    private fun setupRecycler() {
        binding.iconsLegends.setup(
            model = InstructionRecyclerModel(
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                items = listOf(
                    InstructionModel(
                        iconRes = R.drawable.info_icon,
                        instructionText = "Get information"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.meals_icon,
                        instructionText = "List today's meal"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.reset_icon,
                        instructionText = "Reset today's meals"
                    ),
                    InstructionModel(
                        iconRes = R.drawable.add_icon,
                        instructionText = "Add new meal today"
                    ),
                )
            )
        )
    }
}