package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
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
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.dish_recycler.DishRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.model.DishModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ChooseDishScreenFragment : Fragment() {
    private val binding by lazy { FragmentChooseDishScreenBinding.inflate(layoutInflater) }
    private val noItemsLayoutBinding by lazy { NoItemsLayoutBinding.bind(binding.root) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()
    private val dishVm by sharedViewModel<DishViewModel>()

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
        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
        }
    }

    private fun setupNoItemsView() {
        noItemsLayoutBinding.imageView.visibility = View.VISIBLE
        noItemsLayoutBinding.messageView.visibility = View.VISIBLE
        binding.dishRecycler.visibility = View.INVISIBLE

        noItemsLayoutBinding.imageView.setBackgroundResource(R.drawable.empty_menu_icon)
        noItemsLayoutBinding.imageView.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)

        noItemsLayoutBinding.messageView.setup(
            model = TextModelNew(
                textValue = "Seems that you have no dishes yet. \n Start by adding one.",
                textSize = 25,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
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
                primaryColor = themeViewModel.state.value!!.secondaryColor,
                secondaryColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { processDishClick(it) }
            )
        )
    }

    private fun processDishClick(dish: DishModel) {
        navigationVm.navigateTo(DishScreenFragment.newInstance(dish.name, createDish = true), NavigationAction.ADD)
    }

    private fun setupStyling() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.lineView.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)

        binding.sublayoutContainer.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Choose dish",
                textSize = 30,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )
    }

    companion object {
        fun newInstance(): ChooseDishScreenFragment = ChooseDishScreenFragment()
    }
}