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

class MealDetailsScreenFragment : BaseFragment<FragmentMealDetailsScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()

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
        dishVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        themeVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.lineView.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.dishIcon.imageTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
    }

    private fun setupHeader(dishName: String) {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = dishName,
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.details),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
    }

    private fun setupRecycler(nutritionValueModel: NutritionValueModel) {
        binding.metricRecycler.setup(
            MealMetricsModel.newInstance(
                prots = nutritionValueModel.prots / eatenAmount,
                fats = nutritionValueModel.fats  / eatenAmount,
                carbs = nutritionValueModel.carbs  / eatenAmount,
                kcal = nutritionValueModel.kcals  / eatenAmount,
                eaten = eatenAmount,
                editable = false,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            ),
        )
        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.back,
                labelTextSize = Constants.TEXT_SIZE,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { activity?.onBackPressed() },
            ),
        )
    }

    companion object {
        fun newInstance(dishName: String, eatenAmount: Float): MealDetailsScreenFragment {
            return MealDetailsScreenFragment().also {
                it.dishName = dishName
                it.eatenAmount = eatenAmount
            }
        }
    }
}