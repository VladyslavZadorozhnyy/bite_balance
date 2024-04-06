package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.model.DishModel
import com.ui.common.Constants
import com.ui.common.ComponentUiUtils
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.bitebalance.databinding.FragmentDishScreenBinding
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.recycler_views.metric_recycler.DishMetricsModel
import com.ui.basic.recycler_views.metric_recycler.CreateMealWithExistingDishModel

class DishFragment : BaseFragment<FragmentDishScreenBinding>() {
    private val mealVm by sharedViewModel<MealViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()

    private var dishName = ""
    private var editButtonChecked = false
    private var createDish: Boolean = false
    private lateinit var dishNutritionValue: NutritionValueModel

    override fun onStartFragment(): View {
        binding = FragmentDishScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainer)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        dishVm.getDishByName(dishName)
    }

    override fun setupViewModelsObservation() {
        nutritionVm.state.observe(this) { state ->
            if (state.message.isEmpty() && state.data == null) { return@observe }
            if (state.data != null) {
                if (editButtonChecked) { binding.editButton.click() }
                dishNutritionValue = state.data.first()
                setupRecycler()
            }
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        backgroundColor = secondaryColor,
                        textColor = primaryColor,
                        title = state.message,
                        buttonText = R.string.done,
                    ),
                ).show()
            }
        }
        dishVm.state.observe(this) { state ->
            if (state.message.isEmpty() && state.data == null) { return@observe; }

            if (state.message.isEmpty()) {
                setupHeader(state.data!!.first())
                nutritionVm.getNutritionValue(state.data.first().nutritionValId)
            } else {
                ConfirmDialog(requireActivity(), BaseDialogModel(
                    title = state.message,
                    buttonText = R.string.done,
                    textColor = secondaryColor,
                    backgroundColor = primaryColor,
                    onConfirmClicked = { navigationVm.popScreen() },
                )).show()
            }
        }
        mealVm.state.observe(this) { state ->
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModel(
                        title = state.message,
                        buttonText = R.string.done,
                        textColor = primaryColor,
                        backgroundColor = secondaryColor,
                        onConfirmClicked = {
                            if (state.isSuccessful)
                                mActivity.backPressUntil(HomeFragment::class.java.name)
                        },
                    )
                ).show()
            }
        }
        themeVm.state.observe(this) {
            setupStyling()
            if (!createDish) {
                setupEditButton()
                setupDeleteButton()
            }
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        mealVm.state.removeObservers(this)
        dishVm.state.removeObservers(this)
        nutritionVm.resetState()
        nutritionVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.backgroundTintList = ColorStateList.valueOf(secondaryColor)
        binding.lineView.backgroundTintList = ColorStateList.valueOf(secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader(dishModel: DishModel) {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = dishModel.name,
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
                onClickListener = { navigationVm.popScreen() }
            ),
        )
        binding.dishIcon.setBackgroundResource(dishModel.iconRes)
        binding.dishIcon.backgroundTintList = ColorStateList.valueOf(secondaryColor)
    }

    private fun setupSubtitles() {
        binding.toggleCheckbox.visibility = if (editButtonChecked) View.VISIBLE else View.GONE
        if (!editButtonChecked) return
        binding.toggleCheckbox.setup(
            model = TextModel(
                textValue = getString(R.string.toggle_check),
                textSize = Constants.TEXT_SIZE_SMALL,
                textColor = secondaryColor,
                backgroundColor = primaryColor
            ),
        )
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.bin_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                foregroundColor = if (editButtonChecked) secondaryColor else primaryColor,
                backgroundColor = if (editButtonChecked) primaryColor else secondaryColor,
                onClickListener = {
                    YesNoDialog(
                        activity = requireActivity(),
                        model = BaseDialogModel(
                            title = getString(R.string.want_delete),
                            textColor = secondaryColor,
                            backgroundColor = primaryColor,
                            onPositiveClicked = { dishVm.removeDish(dishName) },
                        ),
                    ).show()
                },
            ),
        )
    }

    private fun setupEditButton() {
        binding.editButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.edit_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                foregroundColor = if (editButtonChecked) secondaryColor else primaryColor,
                backgroundColor = if (editButtonChecked) primaryColor else secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    editButtonChecked = !editButtonChecked
                    setupEditButton()
                    setupSubtitles()
                    setupRecycler()
                },
            ),
        )
    }

    private fun processUpdate() {
        nutritionVm.updateNutritionValue(dishName, binding.metricRecycler.getInputValues())
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            if (createDish) {
                CreateMealWithExistingDishModel.newInstance(
                    context = requireContext(),
                    prots = dishNutritionValue.prots.toString(),
                    fats = dishNutritionValue.fats.toString(),
                    carbs = dishNutritionValue.carbs.toString(),
                    kcal = dishNutritionValue.kcals.toString(),
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                )
            } else {
                DishMetricsModel.newInstance(
                    context = requireContext(),
                    editable = editButtonChecked,
                    prots = dishNutritionValue.prots.toString(),
                    fats = dishNutritionValue.fats.toString(),
                    carbs = dishNutritionValue.carbs.toString(),
                    kcal = dishNutritionValue.kcals.toString(),
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                )
            }
        )
        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = if (editButtonChecked) { R.string.update } else { R.string.done },
                labelTextSize = Constants.TEXT_SIZE,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    if (createDish) processCreateDish() else processNonCreateDish()
                }
            ),
        )
    }

    private fun processNonCreateDish() {
        if (!editButtonChecked) {
            navigationVm.popScreen()
            return
        }
        val inputValues = binding.metricRecycler.getInputValues()
        if (inputValues.any { it.isEmpty() }) {
            YesNoDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    title = getString(R.string.some_fields_empty),
                    textColor = secondaryColor,
                    backgroundColor = primaryColor,
                    onPositiveClicked = { processUpdate() },
                ),
            ).show()
        } else {
            processUpdate()
        }
    }

    private fun processCreateDish() {
        val inputValues = binding.metricRecycler.getInputValues().subList(0, 5)
        val eatenValue = binding.metricRecycler.getInputValues().last().ifEmpty { "0.0" }

        if (inputValues.any { inputValues.last().isEmpty() }) {
            YesNoDialog(
                activity = requireActivity(),
                model = BaseDialogModel(
                    title = getString(R.string.some_fields_empty),
                    textColor = secondaryColor,
                    backgroundColor = primaryColor,
                    onPositiveClicked = { mealVm.createNewMeal(dishName, eatenValue.toFloat()) },
                ),
            ).show()
        } else {
            mealVm.createNewMeal(dishName, eatenValue.toFloat())
        }
    }

    companion object {
        fun newInstance(dishName: String, createDish: Boolean = false): DishFragment {
            return DishFragment().also {
                it.dishName = dishName
                it.createDish = createDish
            }
        }
    }
}