package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.FragmentChooseDishScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ChooseDishScreenFragment : Fragment() {
    private val binding by lazy { FragmentChooseDishScreenBinding.inflate(layoutInflater) }
    private val noItemsLayoutBinding by lazy { NoItemsLayoutBinding.bind(binding.root) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
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

    private fun processDishClick(dish: DishModel) {
        navigationVm.navigateTo(DishScreenFragment.newInstance(dish.name, createDish = true), NavigationAction.ADD)
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Choose dish",
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

    companion object {
        fun newInstance(): ChooseDishScreenFragment = ChooseDishScreenFragment()
    }
}