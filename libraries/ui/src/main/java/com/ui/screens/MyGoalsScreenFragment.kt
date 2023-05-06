package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentMyGoalsScreenBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.input_dialog.InputDialog
import com.ui.mocks.MockGoalModel


class MyGoalsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMyGoalsScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeader()
        setupButtons()
        setupRecycler()

        return binding.root
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
                textValue = "My Goals",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )

        binding.monthTextview.setup(
            model = TextModel(
                textValue = "January 2023",
                textSize = 25,
                textColorRes = R.color.white,
                backgroundColor = R.color.black,
            )
        )
    }

    private fun setupButtons() {
        binding.backwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.forwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.addGoalButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 120,
                strokeWidth = 5,
                foregroundColorRes = R.color.black,
                backgroundColorRes = R.color.white,
                onClickListener = { showInputDialog() }
            )
        )
    }

    private fun showInputDialog() {
        InputDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColorRes = R.color.black,
                textColorRes = R.color.white,
                title = "My next goal is to :",
                onInputConfirmed = { Log.d("AAADIP", "confirmButton clicked: $it") },
            )
        ).show()
    }

    private fun setupRecycler() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.black)

        binding.goalRecycler.setup(
            GoalRecyclerModel(
                items = listOf(
                    MockGoalModel(textValue = "One", active = true, achieved = true),
                    MockGoalModel(textValue = "Two", active = false, achieved = true),
                    MockGoalModel(textValue = "Three", active = false, achieved = false),
                    MockGoalModel(textValue = "Four", active = false, achieved = false),
                    MockGoalModel(textValue = "Five", active = true, achieved = false),
                    MockGoalModel(textValue = "One", active = true, achieved = true),
                    MockGoalModel(textValue = "Two", active = false, achieved = true),
                    MockGoalModel(textValue = "Three", active = false, achieved = false),
                    MockGoalModel(textValue = "Four", active = false, achieved = false),
                    MockGoalModel(textValue = "Five", active = true, achieved = false),
                    MockGoalModel(textValue = "One", active = true, achieved = true),
                    MockGoalModel(textValue = "Two", active = false, achieved = true),
                    MockGoalModel(textValue = "Three", active = false, achieved = false),
                    MockGoalModel(textValue = "Four", active = false, achieved = false),
                    MockGoalModel(textValue = "Five", active = true, achieved = false),
                    MockGoalModel(textValue = "One", active = true, achieved = true),
                    MockGoalModel(textValue = "Two", active = false, achieved = true),
                    MockGoalModel(textValue = "Three", active = false, achieved = false),
                    MockGoalModel(textValue = "Four", active = false, achieved = false),
                    MockGoalModel(textValue = "Five", active = true, achieved = false)
                ),
                backgroundColorRes = R.color.black
            )
        )
    }
}