package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentHomeScreenBinding
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.presentation.viewmodels.*
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.ui.components.progress.carousel.ProgressCarouselModel
import com.ui.model.NutritionValueModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeScreenFragment : Fragment() {
    private val binding by lazy { FragmentHomeScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    private val mealVm by sharedViewModel<MealViewModel>()
    private val dateVm by sharedViewModel<DateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupViewModelsObservation()
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

        themeViewModel.state.observe(this) {
            setupStyling()
            setupButtons()
        }

        mealVm.state.observe(this) { state ->
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModel(
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                        textColor = themeViewModel.state.value!!.primaryColor,
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

    private fun setupHeader(greetingValue: String) {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = greetingValue,
                textSize = 35,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor
            )
        )
    }

    private fun setupCarousel(consumedGoalValues: List<NutritionValueModel>) {
        binding.progressCarousel.setup(
            model = ProgressCarouselModel(
                consumed = consumedGoalValues[0],
                goalConsumption = consumedGoalValues[1],
                primaryColor = themeViewModel.state.value!!.primaryColor,
                secondaryColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupButtons() {
        binding.infoButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 100,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(InfoScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.todayMealsButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.meals_icon,
                iconSize = 80,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(TodayMealsScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.resetProgressButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.reset_icon,
                iconSize = 100,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener =  { requestConfirmation() }
            )
        )

        binding.addMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 100,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
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
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                textColor = themeViewModel.state.value!!.primaryColor,
                title = "Would you like to reset progress?\n\nToday’s data will be removed.",
                onPositiveClicked = { mealVm.removeAllMeals() },
            )
        ).show()
    }

    override fun onStop() {
        navigationVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        dateVm.state.removeObservers(this)
        mealVm.state.removeObservers(this)
        super.onStop()
    }

//    override fun onDestroy() {
//        Log.d("AAADIP", "onDestroy in HomeScreenFragment called")
//        navigationVm.state.removeObservers(this)
//        nutritionVm.state.removeObservers(this)
//        dateVm.state.removeObservers(this)
//        mealVm.state.removeObservers(this)
//        super.onDestroy()
//    }
}