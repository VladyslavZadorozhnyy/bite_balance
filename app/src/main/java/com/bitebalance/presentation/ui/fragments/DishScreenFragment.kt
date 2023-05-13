package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.databinding.FragmentDishScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.mocks.MockMetricModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DishScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentDishScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private var editButtonChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupEditButton(editButtonChecked)
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "*Dish name*",
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
        if (editButtonChecked) {
            binding.toggleCheckbox.setup(
                model = TextModel(
                    textValue = "Toggle check box for not including",
                    textSize = 15,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.white
                )
            )
        }

        binding.toggleCheckbox.visibility = if (editButtonChecked) View.VISIBLE else View.GONE
    }

    private fun setupEditButton(isChecked: Boolean) {
        editButtonChecked = isChecked

        binding.editButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.edit_icon,
                iconSize = 70,
                strokeWidth = 5,
                foregroundColorRes = if (editButtonChecked) R.color.black else R.color.white,
                backgroundColorRes = if (editButtonChecked) R.color.white else R.color.black,
                onClickListener = {
                    setupEditButton(!editButtonChecked)
                    setupRecycler()
                    setupSubtitles()
                }
            )
        )
    }

    private fun setupRecycler() {
        binding.metricRecycler.setup(
            MetricRecyclerModel(
                items = listOf(
                    MockMetricModel(
                        name = "Name:",
                        hint = "hint",
                        editable = editButtonChecked
                    ),
                    MockMetricModel(
                        name = "Prots:",
                        hint = "hint",
                        suffix = "g in 100g",
                        editable = editButtonChecked
                    ),
                    MockMetricModel(
                        name = "Fats:",
                        hint = "hint",
                        suffix = "g in 100g",
                        editable = editButtonChecked
                    ),
                    MockMetricModel(
                        name = "Carbs:",
                        hint = "hint",
                        suffix = "g in 100g",
                        editable = editButtonChecked
                    ),
                    MockMetricModel(
                        name = "Kcal:",
                        hint = "hint",
                        suffix = "kcal in 100g",
                        editable = editButtonChecked
                    ),
                    MockMetricModel(
                        name = "Eaten:",
                        hint = "hint",
                        suffix = "in g",
                        editable = editButtonChecked
                    )
                )
            )
        )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = if (editButtonChecked) { R.string.update } else { R.string.done },
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { if (editButtonChecked) {} else { navigationVm.popScreen() } }
            )
        )
    }
}