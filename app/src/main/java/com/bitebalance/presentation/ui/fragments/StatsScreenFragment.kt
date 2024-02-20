package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentStatsScreenBinding
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.StatsViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.graph.component.GraphModel
import com.ui.model.NutritionValueModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.Locale

class StatsScreenFragment : Fragment() {
    private val binding by lazy { FragmentStatsScreenBinding.inflate(layoutInflater) }

    private val statsVm by sharedViewModel<StatsViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()
    private val navigationVm by sharedViewModel<NavigationViewModel>()
    // AAADIP, Remove everywhere "themeViewModel" to "themeVm"
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    private var mToast: Toast? = null
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupViewModelsObservation() {
        statsVm.state.observe(this) { state ->
            if (state.monthNutrition != null && state.goalConsumption != null) {
                setupChart(state.monthNutrition, state.goalConsumption)
            }
        }

        dateVm.state.observe(this) { state ->
            binding.monthTextview.setup(
                model = TextModelNew(
                    textValue = state,
                    textSize = 25,
                    textColor = themeViewModel.state.value!!.primaryColor,
                    backgroundColor = themeViewModel.state.value!!.secondaryColor,
                )
            )
            statsVm.getStatsByMonthAndYear(dateFormat, state)
        }

        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupButtons()
        }
    }

    override fun onPause() {
        super.onPause()
        mToast?.cancel()
        dateVm.state.removeObservers(this)
        statsVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.question_mark_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { showConfirmDialog() }
            )
        )

        binding.toolbar.forwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.goal_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(MyGoalsScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Statistics",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
        dateVm.getCurrentMonth(dateFormat)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getPrevMonth(dateFormat, dateVm.state.value ?: "")
                }
            )
        )

        binding.nxtMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getNextMonth(dateFormat, dateVm.state.value ?: "")
                }
            )
        )
        binding.chooseMonthContainer.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupChart(
        monthNutritionValues: List<NutritionValueModel>,
        goalNutritionValue: NutritionValueModel,
    ) {
        binding.chart.setup(
            GraphModel(
                consumption = monthNutritionValues,
                consumptionGoal = goalNutritionValue,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
        monthNutritionValues.any { it == statsVm.emptyNutritionValue }.let { emptyValPresent ->
            if (emptyValPresent) {
                mToast = Toast.makeText(activity, "Some days do not have data", Toast.LENGTH_LONG)
                mToast?.show()
            }
        }
    }

    private fun showConfirmDialog() {
        ConfirmDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                textColor = themeViewModel.state.value!!.primaryColor,
                title = "Here you can see..."
            )
        ).show()
    }

    override fun onDestroy() {
        mToast?.cancel()
        dateVm.state.removeObservers(this)
        statsVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
        super.onDestroy()
    }
}