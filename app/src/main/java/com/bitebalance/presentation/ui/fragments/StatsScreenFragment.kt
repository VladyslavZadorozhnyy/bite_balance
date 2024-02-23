package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import android.widget.Toast
import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.bitebalance.common.NavigationAction
import com.ui.components.graph.component.GraphModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.StatsViewModel
import com.bitebalance.databinding.FragmentStatsScreenBinding
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StatsScreenFragment : BaseFragment<FragmentStatsScreenBinding>() {
    private val statsVm by sharedViewModel<StatsViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()
    private var mToast: Toast? = null

    override fun onStartFragment(): View {
        binding = FragmentStatsScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainer)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        statsVm.state.observe(this) { state ->
            if (state.monthNutrition != null && state.goalConsumption != null)
                setupChart(state.monthNutrition, state.goalConsumption)
        }
        dateVm.state.observe(this) { state ->
            binding.monthTextview.setup(
                model = TextModel(
                    textValue = state,
                    textSize = Constants.TEXT_SIZE,
                    textColor = themeVm.state.value!!.primaryColor,
                    backgroundColor = themeVm.state.value!!.secondaryColor,
                ),
            )
            statsVm.getStatsByMonthAndYear(Constants.DATE_FORMAT, state)
        }
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupButtons()
        }
    }

    override fun onStopFragment() {
        mToast?.cancel()
        dateVm.state.removeObservers(this)
        statsVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.root.setBackgroundColor(themeVm.state.value!!.secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.question_mark_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { showConfirmDialog() },
            ),
        )
        toolbarBinding.forwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.goal_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(MyGoalsScreenFragment(), NavigationAction.ADD)
                },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.statistics),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        dateVm.getCurrentMonth(Constants.DATE_FORMAT)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getPrevMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "")
                },
            )
        )
        binding.nxtMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getNextMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "")
                },
            )
        )
        binding.chooseMonthContainer.setBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeVm.state.value!!.secondaryColor)
    }

    private fun setupChart(
        monthNutritionValues: List<NutritionValueModel>,
        goalNutritionValue: NutritionValueModel,
    ) {
        binding.chart.setup(
            GraphModel(
                consumption = monthNutritionValues,
                consumptionGoal = goalNutritionValue,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            )
        )
        monthNutritionValues.any { it == statsVm.emptyNutritionValue }.let { emptyValPresent ->
            if (emptyValPresent) {
                mToast = Toast.makeText(activity, requireContext().getString(R.string.days_empty), Toast.LENGTH_LONG)
                mToast?.show()
            }
        }
    }

    private fun showConfirmDialog() {
        ConfirmDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = themeVm.state.value!!.secondaryColor,
                textColor = themeVm.state.value!!.primaryColor,
                title = requireContext().getString(R.string.your_stats_here),
                buttonText = R.string.done,
            ),
        ).show()
    }
}