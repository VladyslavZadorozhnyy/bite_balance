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
        mealVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.mealIcon.imageTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { activity?.onBackPressed() },
            ),
        )
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.add_meal),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.specifyMeal.setup(
            model = TextModel(
                textValue = getString(R.string.specify_meal),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
    }

    private fun setupButtons() {
        binding.createNewButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
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
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(ChooseDishFragment.newInstance(), NavigationAction.ADD)
                },
            ),
        )
        binding.createNewLabel.setup(
            model = TextModel(
                textValue = getString(R.string.create_dish),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.chooseFromMenuLabel.setup(
            model = TextModel(
                textValue = getString(R.string.choose_from_menu),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
    }

    private fun setupMealCounter(mealsNumber: Int) {
        binding.mealsTodayLabel.setup(
            model = TextModel(
                textValue = getString(R.string.meals_eaten_today),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.mealsTodayValue.setup(
            model = TextModel(
                textValue = mealsNumber.toString(),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
    }

    companion object {
        fun newInstance(): AddMealFragment {
            return AddMealFragment()
        }
    }
}