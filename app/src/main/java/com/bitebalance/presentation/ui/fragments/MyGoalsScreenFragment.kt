package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentMyGoalsScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.common.BaseDialogModelNew
import com.ui.components.dialogs.input_dialog.InputDialog
import com.ui.mocks.MockGoalModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MyGoalsScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMyGoalsScreenBinding.inflate(layoutInflater)
    }

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
        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupButtons()
            setupRecycler()
        }
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.chooseMonthContainer.backgroundTintList = ColorStateList.valueOf(
            themeViewModel.state.value!!.primaryColor)

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "My Goals",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )

        binding.monthTextview.setup(
            model = TextModelNew(
                textValue = "January 2023",
                textSize = 25,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.nxtMonthButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.addGoalButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.add_icon,
                iconSize = 120,
                strokeWidth = 5,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { showInputDialog() }
            )
        )
    }

    private fun showInputDialog() {
        InputDialog(
            activity = requireActivity(),
            model = BaseDialogModelNew(
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                textColor = themeViewModel.state.value!!.primaryColor,
                title = "My next goal is to :",
                onInputConfirmed = { Log.d("AAADIP", "confirmButton clicked: $it") },
            )
        ).show()
    }

    private fun setupRecycler() {
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
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }
}