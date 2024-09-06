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

class MenuFragment : BaseFragment<FragmentMenuScreenBinding>() {
    private val dishVm by sharedViewModel<DishViewModel>()
    private var createNewMeal = false

    override fun onStartFragment(): View {
        binding = FragmentMenuScreenBinding.inflate(layoutInflater)
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
            setupHeader()
            setupStyling()
            setupCreateNewButton()
        }
    }

    override fun onStopFragment() {
        super.onStopFragment()
        dishVm.resetState()
        dishVm.state.removeObservers(this)
    }

    private fun setupHeader() {
        toolbarBinding.backButton.visibility = View.GONE
        toolbarBinding.forwardButton.visibility = View.GONE
        toolbarBinding.headline.setup(
            model = TextModel(
                textValue = getString(R.string.menu),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
            ),
        )
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(secondaryColor)
    }

    private fun setupDishRecycler(dishItems: List<DishModel>) {
        noItemsBinding.imageView.visibility = View.INVISIBLE
        noItemsBinding.messageView.visibility = View.INVISIBLE
        binding.dishRecycler.visibility = View.VISIBLE

        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = dishItems,
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onClickListener = { processDishClick(it) },
            ),
        )
    }

    private fun setupCreateNewButton() {
        binding.createNewMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = Constants.ICON_SIZE_MEDIUM,
                foregroundColor = secondaryColor,
                backgroundColor = primaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(
                        CreateNewFragment.newInstance(createDish = true),
                        NavigationAction.ADD,
                    )
                },
            ),
        )
    }

    private fun setupNoItemsView() {
        noItemsBinding.imageView.visibility = View.VISIBLE
        noItemsBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsBinding.imageView.backgroundTintList = ColorStateList.valueOf(primaryColor)
        noItemsBinding.messageView.setup(
            model = TextModel(
                textValue = getString(R.string.no_dishes_yet),
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = primaryColor,
                backgroundColor = secondaryColor,
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
        fun newInstance(creatingNewMeal: Boolean): MenuFragment {
            return MenuFragment().also { it.createNewMeal = creatingNewMeal }
        }
    }
}