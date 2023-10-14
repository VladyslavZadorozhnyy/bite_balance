package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.fragment.app.Fragment
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentStatsScreenBinding
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.StatsViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
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
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.US)

    private val statsVm by sharedViewModel<StatsViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()
    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private var mToast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupButtons()
        setupViewModelsObservation()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
    }

    private fun setupViewModelsObservation() {
        statsVm.state.observe(this) { state ->
            if (state.monthNutrition != null && state.goalConsumption != null) {
                setupChart(state.monthNutrition, state.goalConsumption)
            }
        }

        dateVm.state.observe(this) { state ->
            binding.monthTextview.setup(
                model = TextModel(
                    textValue = state,
                    textSize = 25,
                    textColorRes = R.color.white,
                    backgroundColor = R.color.black,
                )
            )
            statsVm.getStatsByMonthAndYear(dateFormat, state)
        }
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.question_mark_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { showConfirmDialog() }
            )
        )

        binding.toolbar.forwardButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.goal_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    navigationVm.navigateTo(MyGoalsScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Statistics",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
        dateVm.getCurrentMonth(dateFormat)
    }

    private fun setupButtons() {
        binding.prvMonthButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 105,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
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
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    mToast?.cancel()
                    dateVm.getNextMonth(dateFormat, dateVm.state.value ?: "")
                }
            )
        )
    }

    private fun setupChart(
        monthNutritionValues: List<NutritionValueModel>,
        goalNutritionValue: NutritionValueModel,
    ) {
        binding.chart.setup(
            GraphModel(
                consumption = monthNutritionValues,
                consumptionGoal = goalNutritionValue,
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
                backgroundColorRes = R.color.white,
                textColorRes = R.color.black,
                title = "Here you can see..."
            )
        ).show()
    }

    override fun onDestroy() {
        mToast?.cancel()
        statsVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
        super.onDestroy()
    }
}