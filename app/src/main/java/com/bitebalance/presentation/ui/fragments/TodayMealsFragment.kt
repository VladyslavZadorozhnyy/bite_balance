package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.MealModelUnboxed
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.bitebalance.common.NavigationAction
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.databinding.NoItemsLayoutBinding
import com.bitebalance.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ui.basic.recycler_views.meal_recycler.MealRecyclerModel
import com.bitebalance.databinding.FragmentTodaysMealsScreenBinding

class TodayMealsFragment : BaseFragment<FragmentTodaysMealsScreenBinding>() {
    private val mealVm by sharedViewModel<MealViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentTodaysMealsScreenBinding.inflate(layoutInflater)
        noItemsBinding = NoItemsLayoutBinding.bind(binding.root)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        mealVm.getMeals()
    }

    override fun setupViewModelsObservation() {
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
        }
        mealVm.state.observe(this) { state ->
            if (state.data != null && state.data.isNotEmpty()) setupRecycler(state.data)
            if (state.data != null && state.data.isEmpty()) setupNoItemsView()
            else if (state.message.isNotEmpty()) mealVm.getMeals()
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        mealVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.root.setBackgroundColor(secondaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.today_meals),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
    }

    private fun setupNoItemsView() {
        noItemsBinding.imageView.visibility = View.VISIBLE
        noItemsBinding.messageView.visibility = View.VISIBLE
        binding.mealRecycler.visibility = View.INVISIBLE

        noItemsBinding.imageView.setBackgroundResource(R.drawable.nav_menu_active)
        noItemsBinding.imageView.backgroundTintList = ColorStateList.valueOf(secondaryColor)

        noItemsBinding.messageView.setup(
            model = TextModel(
                textValue = getString(R.string.no_meals_yet),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupRecycler(content: List<MealModelUnboxed>) {
        noItemsBinding.imageView.visibility = View.INVISIBLE
        noItemsBinding.messageView.visibility = View.INVISIBLE
        binding.mealRecycler.visibility = View.VISIBLE
        binding.mealRecycler.setup(
            model = MealRecyclerModel(
                items = content,
                backgroundColor = primaryColor,
                foregroundColor = secondaryColor,
                onClickListener = { processMealClick(it) },
                onSwipeListener = { processSwipe(it) },
            ),
        )

    }

    private fun processMealClick(meal: MealModelUnboxed) {
        navigationVm.navigateTo(
            MealDetailsFragment.newInstance(meal.dishName, meal.amount),
            NavigationAction.ADD,
        )
    }

    private fun processSwipe(swipedMeal: MealModelUnboxed) {
        mealVm.removeMeal(swipedMeal)
    }

    companion object {
        fun newInstance(): TodayMealsFragment {
            return TodayMealsFragment()
        }
    }
}