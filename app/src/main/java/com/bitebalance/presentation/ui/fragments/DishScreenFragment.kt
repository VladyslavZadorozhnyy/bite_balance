package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.databinding.FragmentDishScreenBinding
import com.bitebalance.domain.model.NutritionValueModel
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import com.ui.mocks.MockMetricModel
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DishScreenFragment : Fragment() {
    private val binding by lazy { FragmentDishScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()

    private lateinit var dishName: String
    private var editButtonChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupDeleteButton()
        setupEditButton(editButtonChecked)
        setupViewModelsObservation()

        // nutritionVm.getNutritionValue(dishModel.nutritionValId)
        return binding.root
    }

    private fun setupViewModelsObservation() {
        nutritionVm.state.observe(this) { state ->
            // dishNutritionValue = state.nutritionValue
            // setupRecycler()

            if (state.successMessage.isNotEmpty()) {
                binding.editButton.click()
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        backgroundColorRes = R.color.white,
                        textColorRes = R.color.black,
                        title = state.successMessage,
                        buttonTextRes = R.string.done
                    )
                ).show()
            } else if (state.errorMessage.isNotEmpty()) {
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        backgroundColorRes = R.color.white,
                        textColorRes = R.color.black,
                        title = state.errorMessage,
                        buttonTextRes = R.string.done
                    )
                ).show()
            }
        }

        dishVm.state.observe(this) { state ->
            // if (state.isLoading || state.message.isEmpty()) { return@observe; }

            Log.d("AAADIP", "state: $state")
            //setupHeader

            if (!state.isSuccessful) {
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        backgroundColorRes = R.color.white,
                        textColorRes = R.color.black,
                        title = state.message.ifEmpty { "Unknown error happened" },
                        buttonTextRes = R.string.done
                    )
                ).show()
            } else {
                activity?.onBackPressed()
                ConfirmDialog(
                    activity = requireActivity(),
                    model = BaseDialogModel(
                        backgroundColorRes = R.color.white,
                        textColorRes = R.color.black,
                        title = state.message.ifEmpty { "Dish removed successfully" },
                        buttonTextRes = R.string.done
                    )
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
                onClickListener = { navigationVm.popScreen() }
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
                    YesNoDialog(
                        activity = requireActivity(),
                        model = BaseDialogModel(
                            backgroundColorRes = R.color.white,
                            textColorRes = R.color.black,
                            title = "Do you want to delete this dish?",
                            // onPositiveClicked = { dishVm.removeDish(dishModel) },
                        )
                    ).show()
                }
            )
        )
    }

    private fun setupEditButton(isChecked: Boolean) {
        editButtonChecked = isChecked

        binding.editButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.edit_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColorRes = if (editButtonChecked) R.color.black else R.color.white,
                backgroundColorRes = if (editButtonChecked) R.color.white else R.color.black,
                onClickListener = {
                    setupEditButton(!editButtonChecked)
                    // setupRecycler()
                    setupSubtitles()
                }
            )
        )
    }

    private fun processUpdate() {
//        nutritionVm.updateNutritionValue(
//            id = dishModel.nutritionValId,
//            inputValues = binding.metricRecycler.getInputValues()
//        )
    }

    private fun setupRecycler(dishNutritionValue: NutritionValueModel) {
        binding.metricRecycler.setup(
            MetricRecyclerModel(
                items = listOf(
                    MockMetricModel(
                        name = "Prots:",
                        hint = dishNutritionValue?.prots?.toString() ?: "-",
                        suffix = "g in 100g",
                        editable = editButtonChecked,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        hint = dishNutritionValue?.fats?.toString() ?: "-",
                        suffix = "g in 100g",
                        editable = editButtonChecked,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        hint = dishNutritionValue?.carbs?.toString() ?: "-",
                        suffix = "g in 100g",
                        editable = editButtonChecked,
                        onlyNumbers = true,
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        hint = dishNutritionValue?.kcals?.toString() ?: "-",
                        suffix = "kcal in 100g",
                        editable = editButtonChecked,
                        onlyNumbers = true,
                    )
                )
            )
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = if (editButtonChecked) { R.string.update } else { R.string.done },
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    if (editButtonChecked) {
                        val allInputFilled = binding.metricRecycler.getInputValues().all { it.isNotEmpty() }
                        if (allInputFilled) {
                            processUpdate()
                        } else {
                            YesNoDialog(
                                activity = requireActivity(),
                                model = BaseDialogModel(
                                    backgroundColorRes = R.color.white,
                                    textColorRes = R.color.black,
                                    title = "Some fields are empty. They will be filled with '0'",
                                    onPositiveClicked = { processUpdate() },
                                )
                            ).show()
                        }
                    } else {
                        navigationVm.popScreen()
                    }
                }
            )
        )
    }

    override fun onDestroy() {
        dishVm.resetState()
        navigationVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        dishVm.state.removeObservers(this)
        super.onDestroy()
    }

    companion object {
        fun newInstance(dishName: String) = DishScreenFragment().also { it.dishName = dishName }
    }
}