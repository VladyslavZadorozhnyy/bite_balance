package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bitebalance.databinding.FragmentMyGoalsScreenBinding
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.GoalViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.goal_recycler.GoalAdapter
import com.ui.basic.recycler_views.goal_recycler.GoalRecyclerModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.input_dialog.InputDialog
import com.ui.model.GoalModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*


class MyGoalsScreenFragment : Fragment(), GoalAdapter.GoalAdapterListener {
    private val binding by lazy { FragmentMyGoalsScreenBinding.inflate(layoutInflater) }
    private val noGoalsLayoutBinding by lazy { NoItemsLayoutBinding.bind(binding.root) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()
    private val goalVm by sharedViewModel<GoalViewModel>()
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)
    private var currentDateString: String = ""

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
        }

        dateVm.state.observe(this) { state ->
            currentDateString = currentDateString.ifEmpty { state }

            binding.monthTextview.setup(
                model = TextModelNew(
                    textValue = state,
                    textSize = 25,
                    textColor = themeViewModel.state.value!!.secondaryColor,
                    backgroundColor = themeViewModel.state.value!!.primaryColor,
                )
            )
            goalVm.getAllGoals(state, dateFormat)
            binding.addGoalButton.visibility = if (state != currentDateString) View.GONE else View.VISIBLE
        }

        goalVm.state.observe(this) { state ->
            Log.d("AAADIP", "state: $state")
            if (state.message.isNotEmpty() && state.isSuccessful && state.data == null && state.message == "Goal created successfully") {
                goalVm.getAllGoals(dateVm.state.value, dateFormat)
            } else if (state.message.isNotEmpty() && !state.isSuccessful && state.data == null && state.message == "Something went wrong") {
                showConfirmDialog("Something went wrong")
            } else if (state.data != null) {
                setupRecycler()
            }
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
            model = ButtonModel(
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
        dateVm.getCurrentMonth(dateFormat)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { dateVm.getPrevMonth(dateFormat, dateVm.state.value ?: "") }
            )
        )

        binding.nxtMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { dateVm.getNextMonth(dateFormat, dateVm.state.value ?: "") }
            )
        )

        binding.addGoalButton.setup(
            model = ButtonModel(
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
            model = BaseDialogModel(
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                textColor = themeViewModel.state.value!!.primaryColor,
                title = "My next goal is to :",
                onInputConfirmed = { goalVm.addNewGoal(it) },
            )
        ).show()
    }

    private fun showConfirmDialog(message: String) {
        ConfirmDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                textColor = themeViewModel.state.value!!.primaryColor,
                title = message,
                onInputConfirmed = { goalVm.addNewGoal(it) },
            )
        ).show()
    }

    private fun setupRecycler() {
        goalVm.state.value?.data?.let {
            noGoalsLayoutBinding.imageView.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            noGoalsLayoutBinding.messageView.visibility = if (it.isEmpty()) View.VISIBLE else View.INVISIBLE
            binding.goalRecycler.visibility = if (it.isEmpty()) View.INVISIBLE else View.VISIBLE

            if (it.isEmpty()) {
                setupNoItemsView()
            } else {
                binding.goalRecycler.setup(
                    GoalRecyclerModel(
                        items = it,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                        foregroundColor = themeViewModel.state.value!!.primaryColor,
                        goalAdapterListener = this,
                    ),
                )
            }
        }
    }

    private fun setupNoItemsView() {
        noGoalsLayoutBinding.imageView.visibility = View.VISIBLE
        noGoalsLayoutBinding.messageView.visibility = View.VISIBLE
        binding.goalRecycler.visibility = View.INVISIBLE

        noGoalsLayoutBinding.imageView.setBackgroundResource(R.drawable.goal_icon)
        noGoalsLayoutBinding.imageView.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)

        noGoalsLayoutBinding.messageView.setup(
            model = TextModelNew(
                textValue = "Seems that you have no goals for this month. \n\n You can create one for current month.",
                textSize = 25,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
        noGoalsLayoutBinding.root.bringToFront()
        noGoalsLayoutBinding.imageView.bringToFront()
        noGoalsLayoutBinding.messageView.bringToFront()
    }

    override fun checkUncheckItem(goalModel: GoalModel, checked: Boolean) {
        Log.d("AAADIP", "checkUncheckItem, goalModel: $goalModel checked: $checked")
        goalVm.updateGoal(goalModel, checked)
    }

    override fun onItemRemoved(item: GoalModel, itemsLeft: Int) {
        Toast.makeText(activity, "Goal was removed", Toast.LENGTH_SHORT).show()
        goalVm.removeGoal(item)

        if (itemsLeft == 0) setupNoItemsView()
    }
}