package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentProgressScreenBinding
import com.bitebalance.presentation.states.BasicState
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.components.R
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.dialogs.confirm_dialog.ConfirmDialog
import com.ui.model.NutritionValueModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProgressScreenFragment : Fragment() {
    private val binding by lazy { FragmentProgressScreenBinding.inflate(layoutInflater) }
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    private fun setupViewModelsObservation() {
        themeViewModel.state.observe(this) {
            setupStyling()
        }
    }

    private fun setupStyling() {
        binding.sublayoutContainer.backgroundTintList =
            ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)

        binding.root.setBackgroundColor(themeViewModel.state.value!!.primaryColor)
        binding.progressIndicator.trackColor = themeViewModel.state.value!!.primaryColor
        binding.progressIndicator.setIndicatorColor(themeViewModel.state.value!!.secondaryColor)
    }
}