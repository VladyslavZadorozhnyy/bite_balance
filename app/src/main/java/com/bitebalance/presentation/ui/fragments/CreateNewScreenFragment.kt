package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentCreateNewScreenBinding
import com.bitebalance.presentation.viewmodels.DishViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.metric_recycler.DishNameMetricsModel
import com.ui.basic.recycler_views.metric_recycler.MealMetricsModel
import com.ui.basic.texts.common.TextModel
import com.ui.common.ComponentUiUtils
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.components.dialogs.yes_no_dialog.YesNoDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreateNewScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentCreateNewScreenBinding.inflate(layoutInflater)
    }

    private val dishVm by sharedViewModel<DishViewModel>()
    private var createDish = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupSubtitles()
        setupRecycler()
        setupViewModelsObservation()

        return binding.root
    }

    override fun onDestroy() {
        dishVm.resetState()
        dishVm.state.removeObservers(this)
        super.onDestroy()
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)
    }

    private fun setupHeader() {
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = if (createDish) { "New dish" } else { "New meal" },
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                onClickListener = { activity?.onBackPressed() }
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
        binding.metricRecycler.setup( if (createDish)
            DishNameMetricsModel.newInstance() else MealMetricsModel() )

        binding.doneButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.done,
                labelTextSize = 20,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = {
                    ComponentUiUtils.hideKeyBoard(requireActivity())
                    val inputValues = if (createDish) {
                        binding.metricRecycler.getInputValues().subList(0, 5)
                    } else {
                        binding.metricRecycler.getInputValues()
                    }

                    if (inputValues.any { it.isEmpty() }) {
                        YesNoDialog(requireActivity(), BaseDialogModel(
                                title = "Some fields are empty. They will be filled with '0'",
                                onPositiveClicked = { processDishCreation(inputValues) },
                            )).show()
                    } else {
                        processDishCreation(inputValues)
                    }
                }
            )
        )
    }

    private fun processDishCreation(inputValues: List<String>) {
        if (createDish) {
            dishVm.createDish(
                inputValues[0],
                if (inputValues[1].isNotEmpty()) inputValues[1].toFloat() else 0.0f,
                if (inputValues[2].isNotEmpty()) inputValues[2].toFloat() else 0.0f,
                if (inputValues[3].isNotEmpty()) inputValues[3].toFloat() else 0.0f,
                if (inputValues[4].isNotEmpty()) inputValues[4].toFloat() else 0.0f,
            )
        } else {
// AAADIP change later
        }
    }

    private fun setupViewModelsObservation() {
        dishVm.state.observe(this) { state ->
            if (state.isLoading || state.message.isEmpty()) { return@observe }

            ConfirmDialog(
                requireActivity(),
                BaseDialogModel(state.message, buttonTextRes = R.string.done,
                    onConfirmClicked = { if (state.isSuccessful) { activity?.onBackPressed() } })
            ).show()
        }
    }

    companion object {
        fun newInstance(createDish: Boolean): CreateNewScreenFragment =
            CreateNewScreenFragment().also { it.createDish = createDish }
    }
}