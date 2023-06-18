package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentAddMealScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddMealScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentAddMealScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupButtons()
        setupMealCounter()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
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
                textValue = "Add meal",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )

        binding.specifyMeal.setup(
            model = TextModel(
                textValue = "Specify meal, please:",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupButtons() {
        binding.createNewButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    navigationVm.navigateTo(
                        CreateNewScreenFragment.newInstance(createDish = false),
                        NavigationAction.ADD
                    )
                }
            )
        )

        binding.chooseFromMenu.setup(
            model = ButtonModel(
                iconRes = R.drawable.nav_menu_active,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    navigationVm.navigateTo(ChooseDishScreenFragment(), NavigationAction.ADD)
                }
            )
        )

        binding.createNewLabel.setup(
            model = TextModel(
                textValue = "Create new meal",
                textSize = 20,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )

        binding.chooseFromMenuLabel.setup(
            model = TextModel(
                textValue = "Choose from menu",
                textSize = 20,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupMealCounter() {
        binding.mealsTodayLabel.setup(
            model = TextModel(
                textValue = "Meals eaten today: ",
                textSize = 20,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )

        binding.mealsTodayValue.setup(
            model = TextModel(
                textValue = "1",
                textSize = 20,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }
}