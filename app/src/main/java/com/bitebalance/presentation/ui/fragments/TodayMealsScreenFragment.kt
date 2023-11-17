package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentTodaysMealsScreenBinding
import com.ui.model.MealModelUnboxed
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodayMealsScreenFragment : Fragment() {
    private val binding by lazy { FragmentTodaysMealsScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    // AAADIP, Remove everywhere "themeViewModel" to "themeVm"
    private val themeViewModel by sharedViewModel<ThemeViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mealVm.getMeals()
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(
            themeViewModel.state.value!!.primaryColor)

        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupHeader() {
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
                textValue = "Today's meals",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupRecycler(content: List<MealModelUnboxed>) {
        binding.mealRecycler.setup(
            model = MealRecyclerModel(
                items = content,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { processMealClick(it) },
                onSwipeListener = { processSwipe(it) }
            )
        )
    }

    private fun setupViewModelsObservation() {
        themeViewModel.state.observe(this) {
            setupStyling()
            setupHeader()
        }

        mealVm.state.observe(this) { state ->
            if (state.data != null) {
                setupRecycler(state.data)
            } else if (state.message.isNotEmpty()) {
                mealVm.getMeals()
            }
        }
    }

    private fun processMealClick(meal: MealModelUnboxed) {
        navigationVm.navigateTo(
            MealDetailsScreenFragment.newInstance(meal.dishName, meal.amount),
            NavigationAction.ADD
        )
    }

    private fun processSwipe(swipedMeal: MealModelUnboxed) {
        mealVm.removeMeal(swipedMeal)
    }

    override fun onDestroy() {
        navigationVm.state.removeObservers(this)
        mealVm.state.removeObservers(this)
        super.onDestroy()
    }
}