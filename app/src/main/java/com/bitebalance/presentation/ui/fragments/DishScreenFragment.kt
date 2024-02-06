package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.databinding.FragmentDishScreenBinding
import com.bitebalance.presentation.ui.activites.MainActivity
import com.ui.model.NutritionValueModel
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.MealViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.metric_recycler.CreateMealWithExistingDishModel
import com.ui.basic.recycler_views.metric_recycler.DishMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModelNew
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DishScreenFragment : Fragment() {
    private val binding by lazy { FragmentDishScreenBinding.inflate(layoutInflater) }

    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val themeVm by sharedViewModel<ThemeViewModel>()
    private val mealVm by sharedViewModel<MealViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()

    private lateinit var dishName: String
    private var editButtonChecked = false
    private var createDish: Boolean = false
    private lateinit var dishNutritionValue: NutritionValueModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dishVm.getDishByName(dishName)
    }

    private fun setupViewModelsObservation() {
        nutritionVm.state.observe(this) { state ->
            if (state.message.isEmpty() && state.data == null) { return@observe }

            if (state.data != null) {
                if (editButtonChecked) { binding.editButton.click() }
                dishNutritionValue = state.data.first()
                setupRecycler()
            }
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModelNew(
                        backgroundColor = themeVm.state.value!!.secondaryColor,
                        textColor = themeVm.state.value!!.primaryColor,
                        title = state.message,
                        buttonText = R.string.done,
                    )
                ).show()
            }
        }

        dishVm.state.observe(this) { state ->
            if (state.message.isEmpty() && state.data == null) { return@observe; }

            if (state.message.isEmpty()) {
                setupHeader(state.data!!.first())
                nutritionVm.getNutritionValue(state.data.first().nutritionValId)
            } else {
                ConfirmDialog(requireActivity(), BaseDialogModelNew(
                    title = state.message,
                    buttonText = R.string.done,
                    textColor = themeVm.state.value!!.secondaryColor,
                    backgroundColor = themeVm.state.value!!.primaryColor,
                    onConfirmClicked = { activity?.onBackPressed() }
                )).show()
            }
        }

        mealVm.state.observe(this) { state ->
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModelNew(
                        state.message,
                        buttonText = R.string.done,
                        textColor = themeVm.state.value!!.secondaryColor,
                        backgroundColor = themeVm.state.value!!.primaryColor,
                        onConfirmClicked = { if (state.isSuccessful) {
                            (activity as? MainActivity)?.let {
                                it.backPressUntilComponent(HomeScreenFragment::class.java.name)
                            }
                        } })
                ).show()
            }
        }

        themeVm.state.observe(this) { state ->
            setupStyling()
            if (!createDish) {
                setupEditButton()
                setupDeleteButton()
            }
        }
    }

    private fun setupStyling() {
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.lineView.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
    }

    private fun setupHeader(dishModel: DishModel) {
        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = dishModel.name,
                textSize = 30,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { activity?.onBackPressed() }
            )
        )

        binding.dishIcon.setBackgroundResource(dishModel.iconRes)
        binding.dishIcon.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
    }

    private fun setupSubtitles() {
        if (editButtonChecked) {
            binding.toggleCheckbox.setup(
                model = TextModelNew(
                    textValue = "Toggle check box for not including",
                    textSize = 15,
                    textColor = themeVm.state.value!!.secondaryColor,
                    backgroundColor = themeVm.state.value!!.primaryColor
                )
            )
        }

        binding.toggleCheckbox.visibility = if (editButtonChecked) View.VISIBLE else View.GONE
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.bin_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColor = if (editButtonChecked) themeVm.state.value!!.secondaryColor else themeVm.state.value!!.primaryColor,
                backgroundColor = if (editButtonChecked) themeVm.state.value!!.primaryColor else themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    YesNoDialog(
                        requireActivity(),
                        BaseDialogModelNew(
                            title = "Do you want to delete this dish?",
                            textColor = themeVm.state.value!!.secondaryColor,
                            backgroundColor = themeVm.state.value!!.primaryColor,
                            onPositiveClicked = { dishVm.removeDish(dishName) },
                        )).show()
                }
            )
        )
    }

    private fun setupEditButton() {
        binding.editButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.edit_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColor = if (editButtonChecked) themeVm.state.value!!.secondaryColor else themeVm.state.value!!.primaryColor,
                backgroundColor = if (editButtonChecked) themeVm.state.value!!.primaryColor else themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    editButtonChecked = !editButtonChecked
                    setupEditButton()
                    setupSubtitles()
                    setupRecycler()
                }
            )
        )
    }

    private fun processUpdate() {
        nutritionVm.updateNutritionValue(dishName, binding.metricRecycler.getInputValues())
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            if (createDish) {
                CreateMealWithExistingDishModel.newInstance(
                    dishNutritionValue.prots.toString(),
                    dishNutritionValue.fats.toString(),
                    dishNutritionValue.carbs.toString(),
                    dishNutritionValue.kcals.toString(),
                    foregroundColor = themeVm.state.value!!.primaryColor,
                    backgroundColor = themeVm.state.value!!.secondaryColor,
                )
            } else {
                DishMetricsModel.newInstance(
                    editable = editButtonChecked,
                    dishNutritionValue.prots.toString(),
                    dishNutritionValue.fats.toString(),
                    dishNutritionValue.carbs.toString(),
                    dishNutritionValue.kcals.toString(),
                    foregroundColor = themeVm.state.value!!.primaryColor,
                    backgroundColor = themeVm.state.value!!.secondaryColor,
                )
            }
        )

        binding.doneButton.setup(
            model = ButtonModelNew(
                labelTextRes = if (editButtonChecked) { R.string.update } else { R.string.done },
                labelTextSize = 20,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    if (createDish) processCreateDish() else processNonCreateDish()
                }
            )
        )
    }

    private fun processNonCreateDish() {
        if (!editButtonChecked) {
            activity?.onBackPressed()
            return
        }

        val inputValues = binding.metricRecycler.getInputValues()
        if (inputValues.any { it.isEmpty() }) {
            YesNoDialog(
                requireActivity(),
                BaseDialogModelNew(
                    title = "Some fields are empty. They will be filled with '0'",
                    textColor = themeVm.state.value!!.secondaryColor,
                    backgroundColor = themeVm.state.value!!.primaryColor,
                    onPositiveClicked = { processUpdate() },
            )).show()
        } else {
            processUpdate()
        }
    }

    private fun processCreateDish() {
        val inputValues = binding.metricRecycler.getInputValues().subList(0, 5)
        val eatenValue = binding.metricRecycler.getInputValues().last().ifEmpty { "0.0" }

        if (inputValues.any { inputValues.last().isEmpty() }) {
            YesNoDialog(
                requireActivity(),
                BaseDialogModelNew(
                    title = "Some fields are empty. They will be filled with '0'",
                    textColor = themeVm.state.value!!.secondaryColor,
                    backgroundColor = themeVm.state.value!!.primaryColor,
                    onPositiveClicked = { mealVm.createNewMeal(dishName, eatenValue.toFloat()) },
            )).show()
        } else {
            mealVm.createNewMeal(dishName, eatenValue.toFloat())
        }
    }

    override fun onDestroy() {
        dishVm.resetState()
        nutritionVm.resetState()
        nutritionVm.state.removeObservers(this)
        dishVm.state.removeObservers(this)
        super.onDestroy()
    }

    companion object {
        fun newInstance(
            dishName: String,
            createDish: Boolean = false
        ): DishScreenFragment {
            return DishScreenFragment().also {
                it.dishName = dishName
                it.createDish = createDish
            }
        }
    }
}