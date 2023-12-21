package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentTextScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TextScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentTextScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
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
            setupHeader()
            setupSubtext()
        }
    }

    private fun setupHeader() {
        binding.root.setBackgroundColor(themeViewModel.state.value!!.secondaryColor)

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "About Us",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor
            )
        )

        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )
    }

    private fun setupSubtext() {
        binding.subtext.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)

        binding.subtext.setup(
            model = TextModelNew(
                textValue = "*Information about the project and application*",
                textSize = 20,
                textColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor
            )
        )
    }
}