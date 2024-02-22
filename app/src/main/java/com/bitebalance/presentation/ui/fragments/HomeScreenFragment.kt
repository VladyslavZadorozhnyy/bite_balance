package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import com.ui.basic.texts.common.TextModel
import com.bitebalance.common.NavigationAction
import com.ui.basic.buttons.common.ButtonModel
import com.bitebalance.presentation.viewmodels.*
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.presentation.states.BasicState
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.bitebalance.databinding.FragmentHomeScreenBinding
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ui.components.progress.carousel.ProgressCarouselModel

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.root)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        nutritionVm.getConsumedGoalValues()
        dateVm.getGreetingsValue()
    }

    override fun setupViewModelsObservation() {
        nutritionVm.state.observe(this) { state: BasicState<List<NutritionValueModel>> ->
            state.data?.let { data -> setupCarousel(data) }
        }
        dateVm.state.observe(this) { state ->
            setupHeader(state)
        }
        themeVm.state.observe(this) {
            setupStyling()
            setupButtons()
        }
        mealVm.state.observe(this) { state ->
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModel(
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                        textColor = themeVm.state.value!!.primaryColor,
                        title = state.message,
                        buttonText = R.string.done,
                    ),
                ).show()
            }
            if (state.message.isNotEmpty() && state.isSuccessful) {
                nutritionVm.getConsumedGoalValues()
            }
        }
    }

    override fun onStopFragment() {
        themeVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
        mealVm.state.removeObservers(this)
        dateVm.state.removeObservers(this)
    }

    private fun setupHeader(greetingValue: String) {
        toolbarBinding.backButton.visibility = View.GONE
        toolbarBinding.forwardButton.visibility = View.GONE

        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = greetingValue,
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor
            )
        )
    }

    private fun setupCarousel(consumedGoalValues: List<NutritionValueModel>) {
        binding.progressCarousel.setup(
            model = ProgressCarouselModel(
                consumed = consumedGoalValues[0],
                goalConsumption = consumedGoalValues[1],
                primaryColor = themeVm.state.value!!.primaryColor,
                secondaryColor = themeVm.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeVm.state.value!!.secondaryColor)
    }

    private fun setupButtons() {
        binding.infoButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(InfoScreenFragment.newInstance(), NavigationAction.ADD)
                }
            )
        )
        binding.todayMealsButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.meals_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(TodayMealsScreenFragment(), NavigationAction.ADD)
                }
            )
        )
        binding.resetProgressButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.reset_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener =  { requestConfirmation() }
            )
        )
        binding.addMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(AddMealScreenFragment.newInstance(), NavigationAction.ADD)
                },
            )
        )
    }

    private fun requestConfirmation() {
        YesNoDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColor = themeVm.state.value!!.secondaryColor,
                textColor = themeVm.state.value!!.primaryColor,
                title = requireContext().getString(R.string.confirm_reset),
                onPositiveClicked = { mealVm.removeAllMeals() },
            )
        ).show()
    }
}