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


class ChooseDishFragment : BaseFragment<FragmentChooseDishScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()

    override fun onStartFragment(): View {
        binding = FragmentChooseDishScreenBinding.inflate(layoutInflater)
        noItemsBinding = NoItemsLayoutBinding.bind(binding.root)
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
            if (state.data.isEmpty()) setupNoItemsView() else setupDishRecycler(state.data)
        }
        themeVm.state.observe(this) {
            setupStyling()
            setupHeader()
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        dishVm.state.removeObservers(this)
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(secondaryColor)
        binding.lineView.setBackgroundColor(secondaryColor)
        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    private fun setupHeader() {
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.choose_dish),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
        toolbarBinding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = Constants.ICON_SIZE_BIG,
                foregroundColor = primaryColor,
                backgroundColor = secondaryColor,
                onClickListener = { navigationVm.popScreen() },
            ),
        )
    }

    private fun setupNoItemsView() {
        noItemsBinding.imageView.visibility = View.VISIBLE
        noItemsBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsBinding.imageView.backgroundTintList = ColorStateList.valueOf(secondaryColor)

        noItemsBinding.messageView.setup(
            model = TextModel(
                textValue = getString(R.string.no_dishes_yet),
                textSize = Constants.TEXT_SIZE,
                textColor = secondaryColor,
                backgroundColor = primaryColor,
            ),
        )
    }

    private fun setupDishRecycler(dishItems: List<DishModel>) {
        noItemsBinding.imageView.visibility = View.INVISIBLE
        noItemsBinding.messageView.visibility = View.INVISIBLE
        binding.dishRecycler.visibility = View.VISIBLE

        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = dishItems,
                primaryColor = secondaryColor,
                secondaryColor = primaryColor,
                onClickListener = { processDishClick(it) },
            ),
        )
    }

    private fun processDishClick(dish: DishModel) {
        navigationVm.navigateTo(
            nextFragment = DishFragment.newInstance(
                dishName = dish.name,
                createDish = true,
            ),
            navAction = NavigationAction.ADD,
        )
    }

    companion object {
        fun newInstance(): ChooseDishFragment {
            return ChooseDishFragment()
        }
    }
}