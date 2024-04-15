package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import android.widget.Toast
import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.graph.component.GraphModel
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.StatsViewModel
import com.bitebalance.databinding.FragmentStatsScreenBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StatsFragment : BaseFragment<FragmentStatsScreenBinding>() {
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
                    textColor = primaryColor,
                    backgroundColor = secondaryColor,
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
        super.onStopFragment()
        mToast?.cancel()
        dateVm.state.removeObservers(this)
        statsVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.root.setBackgroundColor(secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.visibility = View.INVISIBLE
        toolbarBinding.forwardButton.visibility = View.INVISIBLE

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.statistics),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        dateVm.getCurrentMonth(Constants.DATE_FORMAT)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getPrevMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "")
                },
            ),
        )
        binding.nxtMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getNextMonth(Constants.DATE_FORMAT, dateVm.state.value ?: "")
                },
            ),
        )
        binding.chooseMonthContainer.setBackgroundColor(secondaryColor)
        binding.lineView.setBackgroundColor(secondaryColor)
    }

    private fun setupChart(
        monthNutritionValues: List<NutritionValueModel>,
        goalNutritionValue: NutritionValueModel,
    ) {
        binding.chart.setup(
            GraphModel(
                consumption = monthNutritionValues,
                consumptionGoal = goalNutritionValue,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
            )
        )
        monthNutritionValues.any { it == statsVm.emptyNutritionValue }.let { emptyValPresent ->
            if (emptyValPresent) {
                mToast = Toast.makeText(activity, getString(R.string.days_empty), Toast.LENGTH_LONG)
                mToast?.show()
            }
        }
    }

    companion object {
        fun newInstance(): StatsFragment {
            return StatsFragment()
        }
    }
}