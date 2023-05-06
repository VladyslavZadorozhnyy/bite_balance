package com.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentTextScreenBinding

class TextScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentTextScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupHeadline()
        setupSubtext()

        return binding.root
    }

    private fun setupHeadline() {
        binding.headline.setup(
            model = TextModel(
                textValue = "About Us",
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.backButton.setup(
            model = ButtonModel(
                iconRes = R.drawable.back_button_icon,
                iconSize = 70,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener = { Log.d("AAADIP", "Back button clicked") }
            )
        )
    }

    private fun setupSubtext() {
        binding.subtext.backgroundTintList =
            getColorStateList(requireContext(), R.color.black)

        binding.subtext.setup(
            model = TextModel(
                textValue = "*Information about the project and application*",
                textSize = 20,
                textColorRes = R.color.white,
                backgroundColor = R.color.transparent
            )
        )
    }
}