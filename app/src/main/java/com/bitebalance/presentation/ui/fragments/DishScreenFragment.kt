package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
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
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.CreateMealWithExistingDishModel
import com.ui.basic.recycler_views.metric_recycler.DishMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DishScreenFragment : Fragment() {
    private val binding by lazy { FragmentDishScreenBinding.inflate(layoutInflater) }

    private val nutritionVm by sharedViewModel<NutritionViewModel>()
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
        setupStyling()
        if (!createDish) {
            setupEditButton()
            setupDeleteButton()
        }
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
                ConfirmDialog(requireActivity(), BaseDialogModel(
                    state.message,
                    buttonTextRes = R.string.done,
                )).show()
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
                    buttonTextRes = R.string.done,
                    onConfirmClicked = { activity?.onBackPressed() }
                )).show()
            }
        }

        mealVm.state.observe(this) { state ->
            if (state.message.isNotEmpty()) {
                ConfirmDialog(
                    requireActivity(),
                    BaseDialogModel(state.message, buttonTextRes = R.string.done,
                        onConfirmClicked = { if (state.isSuccessful) {
                            (activity as? MainActivity)?.let {
                                it.backPressUntilComponent(HomeScreenFragment::class.java.name)
                            }
                        } })
                ).show()
            }
        }
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader(dishModel: DishModel) {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = dishModel.name,
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { activity?.onBackPressed() }
            )
        )

        binding.dishIcon.setBackgroundResource(dishModel.iconRes)
    }

    private fun setupSubtitles() {
        if (editButtonChecked) {
            binding.toggleCheckbox.setup(
                model = TextModel(
                    textValue = "Toggle check box for not including",
                    textSize = 15,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.white
                )
            )
        }

        binding.toggleCheckbox.visibility = if (editButtonChecked) View.VISIBLE else View.GONE
    }

    private fun setupDeleteButton() {
        binding.deleteButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.bin_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColorRes = if (editButtonChecked) R.color.black else R.color.white,
                backgroundColorRes = if (editButtonChecked) R.color.white else R.color.black,
                onClickListener = {
                    YesNoDialog(requireActivity(), BaseDialogModel(
                            title = "Do you want to delete this dish?",
                            onPositiveClicked = { dishVm.removeDish(dishName) },
                        )).show()
                }
            )
        )
    }

    private fun setupEditButton() {
        binding.editButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.edit_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColorRes = if (editButtonChecked) R.color.black else R.color.white,
                backgroundColorRes = if (editButtonChecked) R.color.white else R.color.black,
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
                )
            } else {
                DishMetricsModel.newInstance(
                    editable = editButtonChecked,
                    dishNutritionValue.prots.toString(),
                    dishNutritionValue.fats.toString(),
                    dishNutritionValue.carbs.toString(),
                    dishNutritionValue.kcals.toString(),
                )
            }
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = if (editButtonChecked) { R.string.update } else { R.string.done },
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
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
            YesNoDialog(requireActivity(), BaseDialogModel(
                title = "Some fields are empty. They will be filled with '0'",
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
            YesNoDialog(requireActivity(), BaseDialogModel(
                title = "Some fields are empty. They will be filled with '0'",
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