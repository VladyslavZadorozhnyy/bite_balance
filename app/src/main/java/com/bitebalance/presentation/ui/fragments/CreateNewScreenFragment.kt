package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentCreateNewScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.mocks.MockMetricModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreateNewScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentCreateNewScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    private val dishVm by sharedViewModel<DishViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeaded()
        setupSubtitles()
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeaded() {
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Create new",
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
        binding.specifyDish.setup(
            model = TextModel(
                textValue = "Specify nutritional value, please:",
                textSize = 15,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.toggleCheckbox.setup(
            model = TextModel(
                textValue = "Toggle check box for not including",
                textSize = 15,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            MetricRecyclerModel(
                items = listOf(
                    MockMetricModel(
                        name = "Name:",
                        editable = true
                    ),
                    MockMetricModel(
                        name = "Prots:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        suffix = "g in 100g",
                        editable = true,
                        onlyNumbers = true
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        suffix = "kcal in 100g",
                        editable = true,
                        onlyNumbers = true
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        suffix = "in g",
                        editable = true,
                        onlyNumbers = true
                    )
                )
            )
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.done,
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    val inputValues = binding.metricRecycler.getInputValues()


                    dishVm.createDish(
                        inputValues[0],
                        inputValues[1].toFloat(),
                        inputValues[2].toFloat(),
                        inputValues[3].toFloat(),
                        inputValues[4].toFloat(),
                        inputValues[5].toFloat()
                    )
                }
            )
        )
    }
}