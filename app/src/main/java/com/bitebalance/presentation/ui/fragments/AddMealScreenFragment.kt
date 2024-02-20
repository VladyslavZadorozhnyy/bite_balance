package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentAddMealScreenBinding
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddMealScreenFragment : Fragment() {
    private val binding by lazy { FragmentAddMealScreenBinding.inflate(layoutInflater) }

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

    private fun setupViewModelsObservation() {
        mealVm.state.observe(this) { state -> setupMealCounter(state.data?.size ?: 0) }
        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupButtons()
        }
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { activity?.onBackPressed() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Add meal",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)

        binding.specifyMeal.setup(
            model = TextModelNew(
                textValue = "Specify meal, please:",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }

    private fun setupButtons() {
        binding.createNewButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.add_icon,
                iconSize = 100,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(
                        CreateNewScreenFragment.newInstance(createDish = false),
                        NavigationAction.ADD
                    )
                }
            )
        )

        binding.chooseFromMenu.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.nav_menu_active,
                iconSize = 100,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    navigationVm.navigateTo(ChooseDishScreenFragment.newInstance(), NavigationAction.ADD)
                }
            )
        )

        binding.createNewLabel.setup(
            model = TextModelNew(
                textValue = "Create new dish",
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.chooseFromMenuLabel.setup(
            model = TextModelNew(
                textValue = "Choose from menu",
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.mealIcon.imageTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupMealCounter(mealsNumber: Int) {
        binding.mealsTodayLabel.setup(
            model = TextModelNew(
                textValue = "Meals eaten today: ",
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.mealsTodayValue.setup(
            model = TextModelNew(
                textValue = mealsNumber.toString(),
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationVm.state.removeObservers(this)
        mealVm.state.removeObservers(this)
    }
}