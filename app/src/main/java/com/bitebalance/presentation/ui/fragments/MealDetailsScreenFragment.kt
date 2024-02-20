package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.ui.model.NutritionValueModel
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModelNew
import com.ui.basic.buttons.common.ButtonModelNew
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.recycler_views.metric_recycler.MealMetricsModel
import com.bitebalance.databinding.FragmentMealDetailsScreenBinding

class MealDetailsScreenFragment : Fragment() {
    private val binding by lazy { FragmentMealDetailsScreenBinding.inflate(layoutInflater) }

    private val dishVm by sharedViewModel<DishViewModel>()
    private val themeVm by sharedViewModel<ThemeViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()
    private val navigationVm by sharedViewModel<NavigationViewModel>()

    private var dishName: String = ""
    private var eatenAmount: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dishVm.getDishByName(dishName)
    }

    override fun onDestroy() {
        dishVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
        super.onDestroy()
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.lineView.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        binding.dishIcon.imageTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
    }

    private fun setupViewModelsObservation() {
        dishVm.state.observe(this) { dishState ->
            nutritionVm.getNutritionValue(dishState.data?.first()?.nutritionValId ?: -1)
        }

        nutritionVm.state.observe(this) { nutritionState ->
            setupRecycler(nutritionState.data?.first())
        }

        themeVm.state.observe(this) { state ->
            setupStyling()
            setupSubtitles()
            setupHeader(dishName)
        }
    }

    private fun setupHeader(dishName: String) {
        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = dishName,
                textSize = 30,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() },
            )
        )
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModelNew(
                textValue = "Details:",
                textSize = 25,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupRecycler(nutritionValueModel: NutritionValueModel?) {
        binding.metricRecycler.setup(MealMetricsModel.newInstance(
            prots = nutritionValueModel?.prots ?: 0F,
            fats = nutritionValueModel?.fats ?: 0F,
            carbs = nutritionValueModel?.carbs ?: 0F,
            kcal = nutritionValueModel?.kcals ?: 0F,
            eaten = eatenAmount,
            editable = false
        ))

        binding.doneButton.setup(
            model = ButtonModelNew(
                labelTextRes = R.string.back,
                labelTextSize = 20,
                foregroundColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
                onClickListener = { activity?.onBackPressed() },
            )
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