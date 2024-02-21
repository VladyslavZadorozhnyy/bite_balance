package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentCreateNewScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.CreateMealMetricsModel
import com.ui.basic.recycler_views.metric_recycler.DishNameMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreateNewScreenFragment : Fragment() {
    private val binding by lazy { FragmentCreateNewScreenBinding.inflate(layoutInflater) }

    private val themeViewModel by sharedViewModel<ThemeViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()
    private var createDish = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()

        return binding.root
    }

    override fun onDestroy() {
        dishVm.resetState()
        mealVm.resetState() // THIS MAY NOT BE NEEDED AAADIP
        mealVm.state.removeObservers(this)
        dishVm.state.removeObservers(this)
        super.onDestroy()
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.forwardButton.visibility = View.INVISIBLE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = if (createDish) "New dish" else "New meal",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { activity?.onBackPressed() }
            )
        )

        binding.dishIcon.imageTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupSubtitles() {
        binding.specifyDish.setup(
            model = TextModel(
                textValue = "Specify nutritional value, please:",
                textSize = 15,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.toggleCheckbox.setup(
            model = TextModel(
                textValue = "Toggle check box for not including",
                textSize = 15,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            if (createDish) {
                DishNameMetricsModel.newInstance(
                    themeViewModel.state.value!!.primaryColor,
                    themeViewModel.state.value!!.secondaryColor,
                )
            } else {
                CreateMealMetricsModel.newInstance(
                    themeViewModel.state.value!!.primaryColor,
                    themeViewModel.state.value!!.secondaryColor,
                )
            }
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.done,
                labelTextSize = 20,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    val inputValues = if (createDish) {
                        binding.metricRecycler.getInputValues().subList(0, 5)
                    } else {
                        binding.metricRecycler.getInputValues()
                    }

                    if (inputValues.any { it.isEmpty() }) {
                        YesNoDialog(
                            requireActivity(),
                            BaseDialogModel(
                                title = "Some fields are empty. They will be filled with '0'",
                                onPositiveClicked = { processDishCreation(inputValues) },
                                textColor = themeViewModel.state.value!!.primaryColor,
                                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                            ),
                        ).show()
                    } else {
                        processDishCreation(inputValues)
                    }
                }
            )
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

    private fun setupViewModelsObservation() {
        dishVm.state.observe(this) { state ->
            if (state.isLoading || state.message.isEmpty()) { return@observe }

            ConfirmDialog(
                requireActivity(),
                BaseDialogModel(
                    state.message,
                    textColor = themeViewModel.state.value!!.primaryColor,
                    backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    buttonText = R.string.done,
                    onConfirmClicked = { if (state.isSuccessful) activity?.onBackPressed() }),
            ).show()
        }

        mealVm.state.observe(this) { state ->
            if (state.isLoading || state.message.isEmpty()) { return@observe }

            ConfirmDialog(
                requireActivity(),
                BaseDialogModel(
                    state.message,
                    textColor = themeViewModel.state.value!!.primaryColor,
                    backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    buttonText = R.string.done,
                    onConfirmClicked = {
                        if (state.isSuccessful) {
                            activity?.onBackPressed()
                            activity?.onBackPressed()
                        }
                    }
                )
            ).show()
        }

        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupSubtitles()
            setupRecycler()
        }
    }

    companion object {
        fun newInstance(createDish: Boolean): CreateNewScreenFragment =
            CreateNewScreenFragment().also { it.createDish = createDish }
    }
}