package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.model.DishModel
import com.ui.common.Constants
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.bitebalance.common.NavigationAction
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.databinding.NoItemsLayoutBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.bitebalance.databinding.FragmentChooseDishScreenBinding


class ChooseDishScreenFragment : BaseFragment<FragmentChooseDishScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentChooseDishScreenBinding.inflate(layoutInflater)
        noItemsLayoutBinding = NoItemsLayoutBinding.bind(binding.root)
        toolbarBinding = ToolbarBinding.bind(binding.sublayoutContainer)

        return binding.root
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        dishVm.getAllDishes()
    }

    override fun setupViewModelsObservation() {
        dishVm.state.observe(this) { state ->
            if (state.data == null) { return@observe }
            if (state.data.isEmpty()) { setupNoItemsView() } else { setupDishRecycler(state.data) }
        }

        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
        }
    }

    override fun onStopFragment() {
        dishVm.state.removeObservers(this)
        themeVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.choose_dish),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            )
        )

        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.BACK_BUTTON_ICON_SIZE,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { navigationVm.popScreen() },
            )
        )
    }

    private fun setupNoItemsView() {
        noItemsLayoutBinding.imageView.visibility = View.VISIBLE
        noItemsLayoutBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsLayoutBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsLayoutBinding.imageView.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.secondaryColor)

        noItemsLayoutBinding.messageView.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.no_dishes_yet),
                textSize = Constants.TEXT_SIZE,
                textColor = themeVm.state.value!!.secondaryColor,
                backgroundColor = themeVm.state.value!!.primaryColor,
            )
        )
    }

    private fun setupDishRecycler(dishItems: List<DishModel>) {
        noItemsLayoutBinding.imageView.visibility = View.INVISIBLE
        noItemsLayoutBinding.messageView.visibility = View.INVISIBLE
        binding.dishRecycler.visibility = View.VISIBLE

        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = dishItems,
                primaryColor = themeVm.state.value!!.secondaryColor,
                secondaryColor = themeVm.state.value!!.primaryColor,
                onClickListener = { processDishClick(it) },
            )
        )
    }

    private fun processDishClick(dish: DishModel) {
        navigationVm.navigateTo(
            nextFragment = DishScreenFragment.newInstance(dishName = dish.name, createDish = true),
            navAction = NavigationAction.ADD,
        )
    }

    companion object {
        fun newInstance(): ChooseDishScreenFragment {
            return ChooseDishScreenFragment()
        }
    }
}