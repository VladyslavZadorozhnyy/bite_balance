package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentMenuScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuScreenFragment : Fragment() {
    private val binding by lazy { FragmentMenuScreenBinding.inflate(layoutInflater) }
    private val noItemsLayoutBinding by lazy { NoItemsLayoutBinding.bind(binding.root) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    private val dishVm by sharedViewModel<DishViewModel>()
    private var creatingNewMeal = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        dishVm.getAllDishes()
    }

    private fun setupViewModelsObservation() {
        dishVm.state.observe(this) { state ->
            if (state.data == null) { return@observe }
            if (state.data.isEmpty()) { setupNoItemsView() } else { setupDishRecycler(state.data) }
        }

        themeViewModel.state.observe(this) {
            setupHeader()
            setupStyling()
            setupCreateNewButton()
        }
    }

    private fun setupHeader() {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Menu",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupDishRecycler(dishItems: List<DishModel>) {
        noItemsLayoutBinding.imageView.visibility = View.INVISIBLE
        noItemsLayoutBinding.messageView.visibility = View.INVISIBLE
        binding.dishRecycler.visibility = View.VISIBLE

        binding.dishRecycler.setup(
            model = DishRecyclerModel(
                items = dishItems,
                primaryColor = themeViewModel.state.value!!.primaryColor,
                secondaryColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { processDishClick(it) }
            )
        )
    }

    private fun setupCreateNewButton() {
        binding.createNewMealButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.add_icon,
                iconSize = 80,
                strokeWidth = 5,
                labelTextSize = 14,
                labelTextRes = R.string.add_new,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener =  {
                    navigationVm.navigateTo(
                        CreateNewScreenFragment.newInstance(createDish = true),
                        NavigationAction.ADD
                    )
                }
            )
        )
    }

    private fun setupNoItemsView() {
        noItemsLayoutBinding.imageView.visibility = View.VISIBLE
        noItemsLayoutBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsLayoutBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsLayoutBinding.messageView.setup(
            model = TextModelNew(
                textValue = "Seems that you have no dishes yet. \n Start by adding one.",
                textSize = 25,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun processDishClick(dish: DishModel) {
        if (creatingNewMeal) {
            navigationVm.navigateTo(CreateNewScreenFragment.newInstance(createDish = false), NavigationAction.ADD)
        } else {
            navigationVm.navigateTo(DishScreenFragment.newInstance(dish.name), NavigationAction.ADD)
        }
    }

    override fun onDestroy() {
        dishVm.resetState()
        dishVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
        super.onDestroy()
    }

    companion object {
        fun newInstance(creatingNewMeal: Boolean): MenuScreenFragment {
            return MenuScreenFragment().also { it.creatingNewMeal = creatingNewMeal }
        }
    }
}