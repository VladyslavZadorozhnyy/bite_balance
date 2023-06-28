package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bitebalance.databinding.FragmentMealDetailsScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.NutritionViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MealMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.model.NutritionValueModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MealDetailsScreenFragment : Fragment() {
    private val binding by lazy { FragmentMealDetailsScreenBinding.inflate(layoutInflater) }
    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()
    private val nutritionVm by sharedViewModel<NutritionViewModel>()

    private var dishName: String = ""
    private var eatenAmount: Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupSubtitles()
        setupHeader(dishName)
        setupViewModelsObservation()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dishVm.getDishByName(dishName)
    }

    override fun onDestroy() {
        navigationVm.state.removeObservers(this)
        dishVm.state.removeObservers(this)
        nutritionVm.state.removeObservers(this)
        super.onDestroy()
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupViewModelsObservation() {
        dishVm.state.observe(this) { dishState ->
            nutritionVm.getNutritionValue(dishState.data?.first()?.nutritionValId ?: -1)
        }

        nutritionVm.state.observe(this) { nutritionState ->
            setupRecycler(nutritionState.data?.first())
        }
    }

    private fun setupHeader(dishName: String) {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = dishName,
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
    }

    private fun setupSubtitles() {
        binding.details.setup(
            model = TextModel(
                textValue = "Details:",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
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
            model = ButtonModel(
                labelTextRes = R.string.back,
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { activity?.onBackPressed() }
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