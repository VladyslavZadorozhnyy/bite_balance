package com.bitebalance.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentChooseSettingScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.recycler_views.text_recycler.TextRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChooseSettingScreenFragment : Fragment() {

    private val binding by lazy { FragmentChooseSettingScreenBinding.inflate(layoutInflater) }

    private val navigationVm by sharedViewModel<NavigationViewModel>()
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModelsObservation()
        return binding.root
    }

    private fun setupStyling() {
        binding.settingSublayout.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)
        binding.root.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.secondaryColor)
    }

    private fun setupViewModelsObservation() {
        themeViewModel.state.observe(this) { state ->
            setupStyling()
            setupHeader()
            setupRecycler()
        }
    }

    private fun setupHeader() {
        binding.toolbar.backButton.setup(
            model = ButtonModelNew(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColor = themeViewModel.state.value!!.secondaryColor,
                backgroundColor = themeViewModel.state.value!!.primaryColor,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModelNew(
                textValue = "Choosings",
                textSize = 30,
                textColor = themeViewModel.state.value!!.primaryColor,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
            )
        )
    }

    private fun setupRecycler() {
        binding.textRecycler.backgroundTintList = ColorStateList.valueOf(themeViewModel.state.value!!.primaryColor)

        binding.textRecycler.setup(
            model = TextRecyclerModel(
                items = listOf(
                    TextModelNew(
                        textValue = "Appearance",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "Language",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "Measurement",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "About Us",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "Instruction",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "Support Us",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                    TextModelNew(
                        textValue = "Feedback",
                        textSize = 30,
                        textColor = themeViewModel.state.value!!.primaryColor,
                        backgroundColor = themeViewModel.state.value!!.secondaryColor,
                    ),
                ),
                onClickListener = { processVariantClicked(it) }
            )
        )
    }

    private fun processVariantClicked(variant: TextModelNew) {
        Log.d("AAADIP", "variant: $variant")
    }
}