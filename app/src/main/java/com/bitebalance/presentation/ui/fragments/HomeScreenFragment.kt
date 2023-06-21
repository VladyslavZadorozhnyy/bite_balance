package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentHomeScreenBinding
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.presentation.viewmodels.DateViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.ui.components.progress.carousel.ProgressCarouselModel
import com.ui.model.NutritionValueModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeScreenFragment : Fragment() {
    private val binding by lazy { FragmentHomeScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupButtons()
        setupViewModelsObservation()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        nutritionVm.getConsumedGoalValues()
        dateVm.getGreetingsValue()
    }

    private fun setupViewModelsObservation() {
        nutritionVm.state.observe(this) { state: BasicState<List<NutritionValueModel>> ->
            state.data?.let { data -> setupCarousel(data) }
        }

        dateVm.state.observe(this) { state: String ->
            setupHeader(state)
        }
    }

    private fun setupHeader(greetingValue: String) {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = greetingValue,
                textSize = 35,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )
    }

    private fun setupCarousel(consumedGoalValues: List<NutritionValueModel>) {
        binding.progressCarousel.setup(
            model = ProgressCarouselModel(
                consumed = consumedGoalValues[0],
                goalConsumption = consumedGoalValues[1],
            )
        )
    }

    private fun setupButtons() {
        binding.infoButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    navigationVm.navigateTo(InfoScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.todayMealsButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.meals_icon,
                iconSize = 80,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    navigationVm.navigateTo(TodaysMealsScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.resetProgressButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.reset_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  { requestConfirmation() }
            )
        )

        binding.addMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    navigationVm.navigateTo(AddMealScreenFragment(), NavigationAction.ADD)
                }
            )
        )
    }

    private fun requestConfirmation() {
        YesNoDialog(
            activity = requireActivity(),
            model = BaseDialogModel(
                backgroundColorRes = R.color.white,
                textColorRes = R.color.black,
                title = "Would you like to reset progress?\n\nToday’s data will be removed.",
                onPositiveClicked = { Log.d("AAADIP", "onPositive clicked") },
                onNegativeClicked = { Log.d("AAADIP", "onNegative clicked") }
            )
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        dateVm.state.removeObservers(this)
    }
}