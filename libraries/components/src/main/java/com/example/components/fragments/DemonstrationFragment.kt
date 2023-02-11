package com.example.components.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.components.R
import com.example.components.buttons.common.BaseButtonModel
import com.example.components.databinding.FragmentDemonstrationBinding
import com.example.components.texts.common.BaseTextModel

class DemonstrationFragment : Fragment() {
    private val binding by lazy { FragmentDemonstrationBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTitle()
        setupTextSample()
        setupSlideableTextSample()
        setupTextIconButtonSample()
        setupTextButtonSample()
        setupIconButtonSample()
    }

    private fun setupTitle() {
        binding.title.setup(
            model = BaseTextModel(
                textValue = "Demonstration Fragment",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextSample() {
        binding.textSample.setup(
            model = BaseTextModel(
                textValue = "Text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupSlideableTextSample() {
        binding.slideableTextSample.setup(
            model = BaseTextModel(
                textValue = "Very very long text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextIconButtonSample() {
        binding.textIconButtonSample.setup(
            model = BaseButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 80,
                labelTextSize = 14,
                labelTextRes = R.string.text_icon_button_sample,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"TextIconButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun setupTextButtonSample() {
        binding.textButtonSample.setup(
            model = BaseButtonModel(
                labelTextSize = 20,
                labelTextRes = R.string.text_button_sample,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"TextButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }

    private fun setupIconButtonSample() {
        binding.iconButtonSample.setup(
            model = BaseButtonModel(
                iconRes = R.drawable.info_icon,
                iconSize = 100,
                foregroundColorRes = R.color.white,
                backgroundColorRes = R.color.black,
                onClickListener =  {
                    Toast.makeText(activity,"IconButton clicked!", Toast.LENGTH_SHORT).show()
                }
            )
        )
    }
}