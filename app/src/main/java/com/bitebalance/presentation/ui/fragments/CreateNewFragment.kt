package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.common.ComponentUiUtils
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.databinding.FragmentCreateNewScreenBinding
import com.ui.basic.recycler_views.metric_recycler.DishNameMetricsModel
import com.ui.basic.recycler_views.metric_recycler.CreateMealMetricsModel

class CreateNewFragment : BaseFragment<FragmentCreateNewScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()
    private var createDish = false

    override fun onStartFragment(): View {
        binding = FragmentCreateNewScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun setupViewModelsObservation() {
        dishVm.state.observe(this) { state ->
            if (state.isLoading || state.message.isEmpty()) { return@observe }

            ConfirmDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    title = state.message,
                    textColor = primaryColor,
                    backgroundColor = secondaryColor,
                    buttonText = R.string.done,
                    onConfirmClicked = { if (state.isSuccessful) navigationVm.popScreen() },
                ),
            ).show()

            if (state.isSuccessful) dishVm.getAllDishes()
        }

        mealVm.state.observe(this) { state ->
            if (state.isLoading || state.message.isEmpty()) { return@observe }

            ConfirmDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    title = state.message,
                    textColor = primaryColor,
                    backgroundColor = secondaryColor,
                    buttonText = R.string.done,
                    onConfirmClicked = {
                        if (state.isSuccessful)
                            mActivity.backPressUntil(HomeFragment::class.java.name)
                    }
                ),
            ).show()
        }
        themeVm.state.observe(this) {
            setupHeader()
            setupStyling()
            setupRecycler()
            setupSubtitles()
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        dishVm.state.removeObservers(this)
        mealVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        toolbarBinding.forwardButton.visibility = View.INVISIBLE
        binding.root.setBackgroundColor(secondaryColor)
        binding.lineView.setBackgroundColor(secondaryColor)
        binding.dishIcon.imageTintList = ColorStateList.valueOf(secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(if (createDish) R.string.new_dish else R.string.new_meal),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupSubtitles() {
        binding.specifyDish.setup(
            model = TextModel(
                textValue = getString(R.string.spec_nut_value),
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        binding.toggleCheckbox.setup(
            model = TextModel(
                textValue = getString(R.string.toggle_check),
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            if (createDish) {
                DishNameMetricsModel.newInstance(
                    context = requireContext(),
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                )
            } else {
                CreateMealMetricsModel.newInstance(
                    context = requireContext(),
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                )
            }
        )
        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.done,
                labelTextSize = Constants.TEXT_SIZE,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())

                    val inputValues = if (createDish)
                        binding.metricRecycler.getInputValues().subList(0, 5)
                    else
                        binding.metricRecycler.getInputValues()

                    if (inputValues.any { it.isEmpty() }) {
                        YesNoDialog(
                            activity = requireActivity(),
                            model = BaseDialogModel(
                                title = getString(R.string.some_fields_empty),
                                textColor = primaryColor,
                                backgroundColor = secondaryColor,
                                onPositiveClicked = { processDishCreation(inputValues) },
                            ),
                        ).show()
                    } else {
                        processDishCreation(inputValues)
                    }
                }
            ),
        )
    }

    private fun processDishCreation(inputValues: List<String>) {
        if (createDish) {
            dishVm.createDish(
                inputValues[0],
                if (inputValues[1].isNotEmpty()) inputValues[1].toFloat() else 0.0f,
                if (inputValues[2].isNotEmpty()) inputValues[2].toFloat() else 0.0f,
                if (inputValues[3].isNotEmpty()) inputValues[3].toFloat() else 0.0f,
                if (inputValues[4].isNotEmpty()) inputValues[4].toFloat() else 0.0f,
            )
        } else {
            mealVm.createNewDishAndMeal(
                inputValues[0].ifEmpty { "0" },
                if (inputValues[1].isNotEmpty()) inputValues[1].toFloat() else 0.0f,
                if (inputValues[2].isNotEmpty()) inputValues[2].toFloat() else 0.0f,
                if (inputValues[3].isNotEmpty()) inputValues[3].toFloat() else 0.0f,
                if (inputValues[4].isNotEmpty()) inputValues[4].toFloat() else 0.0f,
                if (inputValues[5].isNotEmpty()) inputValues[5].toFloat() else 0.0f,
            )
        }
    }

    companion object {
        fun newInstance(createDish: Boolean): CreateNewFragment {
            return CreateNewFragment().also { it.createDish = createDish }
        }
    }
}