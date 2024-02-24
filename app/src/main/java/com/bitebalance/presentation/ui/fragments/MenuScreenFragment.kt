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
import com.bitebalance.databinding.FragmentMenuScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel

class MenuScreenFragment : BaseFragment<FragmentMenuScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()
    private var createNewMeal = false

    override fun onStartFragment(): View {
        binding = FragmentMenuScreenBinding.inflate(layoutInflater)
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
            setupHeader()
            setupStyling()
            setupCreateNewButton()
        }
    }

    override fun onStopFragment() {
        dishVm.resetState()
        dishVm.state.removeObservers(this)
        themeVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.visibility = View.GONE
        toolbarBinding.forwardButton.visibility = View.GONE
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.menu),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeVm.state.value!!.secondaryColor)
    }

    private fun setupDishRecycler(dishItems: List<DishModel>) {
        noItemsLayoutBinding.imageView.visibility = View.INVISIBLE
        noItemsLayoutBinding.messageView.visibility = View.INVISIBLE
        binding.dishRecycler.visibility = View.VISIBLE

        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = dishItems,
                primaryColor = themeVm.state.value!!.primaryColor,
                secondaryColor = themeVm.state.value!!.secondaryColor,
                onClickListener = { processDishClick(it) },
            ),
        )
    }

    private fun setupCreateNewButton() {
        binding.createNewMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                labelTextSize = Constants.TEXT_SIZE_SMALL,
                labelTextRes = R.string.add_new,
                foregroundColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(
                        CreateNewFragment.newInstance(createDish = true),
                        NavigationAction.ADD
                    )
                },
            ),
        )
    }

    private fun setupNoItemsView() {
        noItemsLayoutBinding.imageView.visibility = View.VISIBLE
        noItemsLayoutBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsLayoutBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsLayoutBinding.imageView.backgroundTintList = ColorStateList.valueOf(themeVm.state.value!!.primaryColor)
        noItemsLayoutBinding.messageView.setup(
            model = TextModel(
                textValue = requireContext().getString(R.string.no_dishes_yet),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = themeVm.state.value!!.primaryColor,
                backgroundColor = themeVm.state.value!!.secondaryColor,
            ),
        )
    }

    private fun processDishClick(dish: DishModel) {
        if (createNewMeal)
            navigationVm.navigateTo(CreateNewFragment.newInstance(createDish = false), NavigationAction.ADD)
        else
            navigationVm.navigateTo(DishFragment.newInstance(dish.name), NavigationAction.ADD)
    }

    companion object {
        fun newInstance(creatingNewMeal: Boolean): MenuScreenFragment {
            return MenuScreenFragment().also { it.createNewMeal = creatingNewMeal }
        }
    }
}