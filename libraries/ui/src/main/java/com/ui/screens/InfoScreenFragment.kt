package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.instruction_recycler.InstructionRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentInfoScreenBinding
import com.ui.mocks.MockInstructionModel

class InfoScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentInfoScreenBinding.inflate(layoutInflater)
    }

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
        binding.sublayoutContainer.backgroundTintList =
            ContextCompat.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.headline.setup(
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