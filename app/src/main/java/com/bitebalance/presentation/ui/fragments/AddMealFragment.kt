package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.bitebalance.common.NavigationAction
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.databinding.FragmentAddMealScreenBinding

class AddMealFragment : BaseFragment<FragmentAddMealScreenBinding>() {
    private val mealVm by sharedViewModel<MealViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentAddMealScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainer)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        mealVm.getMeals()
    }

    override fun setupViewModelsObservation() {
        mealVm.state.observe(this) { state -> setupMealCounter(state.data?.size ?: 0) }
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
            setupButtons()
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        mealVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(secondaryColor)
        binding.lineView.setBackgroundColor(secondaryColor)
        binding.mealIcon.imageTintList = ColorStateList.valueOf(secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.add_meal),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        binding.specifyMeal.setup(
            model = TextModel(
                textValue = getString(R.string.specify_meal),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupButtons() {
        binding.createNewButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(
                        CreateNewFragment.newInstance(createDish = false),
                        NavigationAction.ADD,
                    )
                },
            ),
        )
        binding.chooseFromMenu.setup(
            model = ButtonModel(
                iconRes = R.drawable.nav_menu_active,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(ChooseDishFragment.newInstance(), NavigationAction.ADD)
                },
            ),
        )
        binding.createNewLabel.setup(
            model = TextModel(
                textValue = getString(R.string.create_dish),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        binding.chooseFromMenuLabel.setup(
            model = TextModel(
                textValue = getString(R.string.choose_from_menu),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupMealCounter(mealsNumber: Int) {
        binding.mealsTodayLabel.setup(
            model = TextModel(
                textValue = getString(R.string.meals_eaten_today),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        binding.mealsTodayValue.setup(
            model = TextModel(
                textValue = mealsNumber.toString(),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    companion object {
        fun newInstance(): AddMealFragment {
            return AddMealFragment()
        }
    }
}