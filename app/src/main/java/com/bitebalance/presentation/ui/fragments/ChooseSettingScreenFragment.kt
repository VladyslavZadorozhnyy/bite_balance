package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.bitebalance.databinding.FragmentChooseSettingScreenBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.recycler_views.text_recycler.TextRecyclerModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChooseSettingScreenFragment : Fragment() {

    private val binding by lazy {
        FragmentChooseSettingScreenBinding.inflate(layoutInflater)
    }

    private val navigationVm by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupStyling()
        setupHeader()
        setupRecycler()

        return binding.root
    }

    private fun setupStyling() {
        binding.settingSublayout.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.black)
    }

    private fun setupHeader() {
        binding.toolbar.forwardButton.visibility = View.GONE

        binding.toolbar.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { navigationVm.popScreen() }
            )
        )

        binding.toolbar.headline.setup(
            model = TextModel(
                textValue = "Choosings",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
            )
        )
    }

    private fun setupRecycler() {
        binding.textRecycler.setup(
            model = TextRecyclerModel(
                items = listOf(
                    TextModel(
                        textValue = "Appearance",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "Language",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "Measurement",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "About Us",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "Instruction",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "Support Us",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                    TextModel(
                        textValue = "Feedback",
                        textSize = 30,
                        textColorRes = R.color.black,
                        backgroundColor = R.color.white,
                    ),
                ),
                onClickListener = { processVariantClicked(it) }
            )
        )
    }

    private fun processVariantClicked(variant: TextModel) {
        Log.d("AAADIP", "variant: $variant")
    }
}