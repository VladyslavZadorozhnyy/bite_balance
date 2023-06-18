package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentMenuScreenBinding
import com.bitebalance.presentation.viewmodels.MenuViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MenuScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentMenuScreenBinding.inflate(layoutInflater)
    }

    private val noItemsLayoutBinding by lazy {
        NoItemsLayoutBinding.bind(binding.root)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val menuVm by sharedViewModel<MenuViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeader()
        setupCreateNewButton()
        observeViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Log.d("AAADIP", "onResume() called")
        menuVm.getAllDishes()
    }

    private fun observeViewModel() {
        menuVm.state.observe(this) { state ->
            Log.d("AAADIP", "state: $state")
            if (state.dishes == null) { return@observe }

            if (state.dishes.isEmpty()) {
                setupNoItemsView()
            } else {
                setupDishRecycler(state.dishes)
            }
        }
    }

    private fun setupHeader() {
        binding.toolbar.backButton.visibility = View.GONE
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Menu",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
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
                onClickListener = { processDishClick(it) }
            )
        )
    }

    private fun setupCreateNewButton() {
        binding.createNewMealButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.add_icon,
                iconSize = 80,
                strokeWidth = 5,
                labelTextSize = 14,
                labelTextRes = R.string.add_new,
                foregroundColorRes = R.color.black,
                backgroundColorRes = R.color.white,
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
            model = TextModel(
                textValue = "Seems that you have no dishes yet. \n Start by adding one.",
                textSize = 25,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun processDishClick(dish: DishModel) {
        navigationVm.navigateTo(DishScreenFragment.newInstance(dish.name), NavigationAction.ADD)
    }
}