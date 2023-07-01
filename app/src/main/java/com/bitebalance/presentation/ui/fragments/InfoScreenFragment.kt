package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentInfoScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.instruction_recycler.InstructionRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockInstructionModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class InfoScreenFragment : Fragment() {
    private val binding by lazy { FragmentInfoScreenBinding.inflate(layoutInflater) }
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
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Icons Legend",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupRecycler() {
        binding.iconsLegends.setup(
            model = InstructionRecyclerModel(
                backgroundColorRes = R.color.white,
                items = listOf(
                    MockInstructionModel(
                        iconRes = R.drawable.info_icon,
                        instructionText = "Get information"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.meals_icon,
                        instructionText = "List today's meal"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.reset_icon,
                        instructionText = "Reset today's meals"
                    ),
                    MockInstructionModel(
                        iconRes = R.drawable.add_icon,
                        instructionText = "Add new meal today"
                    ),
                )
            )
        )
    }
}