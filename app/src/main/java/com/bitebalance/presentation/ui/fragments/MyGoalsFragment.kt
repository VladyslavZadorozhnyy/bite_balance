package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import android.widget.Toast
import com.ui.model.GoalModel
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.input_dialog.InputDialog
import com.ui.components.databinding.NoItemsLayoutBinding
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.GoalViewModel
import com.ui.basic.recycler_views.goal_recycler.GoalAdapter
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.databinding.FragmentMyGoalsScreenBinding
import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel


class MyGoalsFragment : BaseFragment<FragmentMyGoalsScreenBinding>(), GoalAdapter.GoalAdapterListener {
    private val dateVm by sharedViewModel<DateViewModel>()
    private val goalVm by sharedViewModel<GoalViewModel>()
    private var curDate = ""

    override fun onStartFragment(): View {
        binding = FragmentMyGoalsScreenBinding.inflate(layoutInflater)
        noItemsBinding = NoItemsLayoutBinding.bind(binding.root)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupButtons()
        }
        dateVm.state.observe(this) { state ->
            curDate = curDate.ifEmpty { state }

            binding.monthTextview.setup(
                model = TextModel(
                    textValue = state,
                    textSize = Constants.TEXT_SIZE,
                    textColor = secondaryColor,
                    backgroundColor = primaryColor,
                ),
            )
            goalVm.getAllGoals(state, Constants.DATE_FORMAT)
            binding.addGoalButton.visibility = if (state != curDate) View.GONE else View.VISIBLE
        }
        goalVm.state.observe(this) { state ->
            if (state.data != null)
                setupRecycler()
            else if (state.isSuccessful && state.message == "Goal created successfully")
                goalVm.getAllGoals(dateVm.state.value, Constants.DATE_FORMAT)
            else if (!state.isSuccessful && state.message == "Something went wrong")
                showConfirmDialog(state.message)
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        dateVm.state.removeObservers(this)
        goalVm.state.removeObservers(this)
    }

    override fun checkUncheckItem(goalModel: GoalModel, checked: Boolean) {
        goalVm.updateGoal(goalModel, checked)
    }

    override fun onItemRemoved(item: GoalModel, itemsLeft: Int) {
        Toast.makeText(activity, getString(R.string.goal_removed), Toast.LENGTH_SHORT).show()
        goalVm.removeGoal(item)

        if (itemsLeft == 0) setupNoItemsView()
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader() {
        binding.lineView.setBackgroundColor(secondaryColor)
        binding.chooseMonthContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)

        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() }
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.my_goals),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        dateVm.getCurrentMonth(Constants.DATE_FORMAT)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { dateVm.getPrevMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "") },
            ),
        )
        binding.nxtMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { dateVm.getNextMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "") },
            ),
        )
        binding.addGoalButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_LARGE,
                strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = { showInputDialog() }
            ),
        )
    }

    private fun showInputDialog() {
        InputDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = secondaryColor,
                textColor = primaryColor,
                title = getString(R.string.next_goal),
                onInputConfirmed = { goalVm.addNewGoal(it) },
            ),
        ).show()
    }

    private fun showConfirmDialog(message: String) {
        ConfirmDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = secondaryColor,
                textColor = primaryColor,
                title = message,
                onInputConfirmed = { goalVm.addNewGoal(it) },
            ),
        ).show()
    }

    private fun setupRecycler() {
        goalVm.state.value?.data?.let {
            noItemsBinding.imageView.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            noItemsBinding.messageView.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            binding.goalRecycler.visibility = if (it.isEmpty()) View.INVISIBLE else View.VISIBLE

            if (it.isEmpty()) {
                setupNoItemsView()
            } else {
                binding.goalRecycler.setup(
                    model = GoalRecyclerModel(
                        items = it,
                        backgroundColor = secondaryColor,
                        foregroundColor = primaryColor,
                        goalAdapterListener = this,
                    ),
                )
            }
        }
    }

    private fun setupNoItemsView() {
        noItemsBinding.imageView.visibility = View.VISIBLE
        noItemsBinding.messageView.visibility = View.VISIBLE
        binding.goalRecycler.visibility = View.INVISIBLE

        noItemsBinding.imageView.setBackgroundResource(R.drawable.goal_icon)
        noItemsBinding.imageView.backgroundTintList = ColorStateList.valueOf(secondaryColor)
        noItemsBinding.messageView.setup(
            model = TextModel(
                textValue = getString(R.string.no_goals_yet),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        noItemsBinding.root.bringToFront()
        noItemsBinding.imageView.bringToFront()
        noItemsBinding.messageView.bringToFront()
    }

    companion object {
        fun newInstance(): MyGoalsFragment {
            return MyGoalsFragment()
        }
    }
}