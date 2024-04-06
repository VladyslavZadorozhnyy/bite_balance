package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.recycler_views.metric_recycler.MealMetricsModel
import com.bitebalance.databinding.FragmentMealDetailsScreenBinding

class MealDetailsFragment : BaseFragment<FragmentMealDetailsScreenBinding>() {
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()

    private var dishName: String = ""
    private var eatenAmount: Float = 0F

    override fun onStartFragment(): View {
        binding = FragmentMealDetailsScreenBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainerConstraint)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        dishVm.getDishByName(dishName)
    }

    override fun setupViewModelsObservation() {
        dishVm.state.observe(this) { dishState ->
            nutritionVm.getNutritionValue(dishState.data?.first()?.nutritionValId ?: -1)
        }
        nutritionVm.state.observe(this) { nutritionState ->
            nutritionState.data?.first()?.let { setupRecycler(it) }
        }
        themeVm.state.observe(this) {
            setupStyling()
            setupSubtitles()
            setupHeader(dishName)
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        dishVm.state.removeObservers(this)
        nutritionVm.resetState()
        nutritionVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(secondaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.lineView.backgroundTintList = ColorStateList.valueOf(primaryColor)
        binding.dishIcon.imageTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader(dishName: String) {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = dishName,
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModel(
                textValue = getString(R.string.details),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
    }

    private fun setupRecycler(nutritionValueModel: NutritionValueModel) {
        binding.metricRecycler.setup(
            MealMetricsModel.newInstance(
                context = requireContext(),
                prots = nutritionValueModel.prots / eatenAmount,
                fats = nutritionValueModel.fats  / eatenAmount,
                carbs = nutritionValueModel.carbs  / eatenAmount,
                kcal = nutritionValueModel.kcals  / eatenAmount,
                eaten = eatenAmount,
                editable = false,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.back,
                labelTextSize = Constants.TEXT_SIZE,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    companion object {
        fun newInstance(dishName: String, eatenAmount: Float): MealDetailsFragment {
            return MealDetailsFragment().also {
                it.dishName = dishName
                it.eatenAmount = eatenAmount
            }
        }
    }
}