package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentAddMealScreenBinding

class AddMealScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentAddMealScreenBinding.inflate(layoutInflater)
    }

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
        binding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.headline.setup(
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
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )

        binding.chooseFromMenu.setup(
            model = ButtonModel(
                iconRes = R.drawable.nav_menu_active,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
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
                textValue = "Meals today: ",
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