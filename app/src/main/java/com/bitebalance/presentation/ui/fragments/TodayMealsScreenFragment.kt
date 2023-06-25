package com.bitebalance.presentation.ui.fragments

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
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TodayMealsScreenFragment : Fragment() {
    private val binding by lazy { FragmentTodaysMealsScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupViewModelsObservation()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mealVm.getMeals()
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.black)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Today's meals",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupRecycler(content: List<MealModelUnboxed>) {
        binding.mealRecycler.setup(
            model = MealRecyclerModel(
                items = content,
                backgroundColorRes = R.color.black,
                onClickListener = { processMealClick(it) },
                onSwipeListener = { processSwipe(it) }
            )
        )
    }

    private fun setupViewModelsObservation() {
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
            MealDetailsScreenFragment.newInstance(meal.id, meal.amount),
            NavigationAction.ADD
        )
    }

    private fun processSwipe(swipedMeal: MealModelUnboxed) {
        mealVm.removeMeal(swipedMeal)
    }
}