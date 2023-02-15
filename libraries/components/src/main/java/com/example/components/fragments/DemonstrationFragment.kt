package com.example.components.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.FragmentDemonstrationBinding
import com.example.components.progress.indicator.ProgressIndicatorModel
import com.example.components.mocks.MockNutritionModel
import com.example.components.progress.carousel.ProgressCarouselModel
import com.example.components.texts.common.TextModel

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

        binding.progressCarousel.setup(model = ProgressCarouselModel(
            consumed = MockNutritionModel(
                fat = 10F,
                carb = 14F,
                ccal = 750F,
                protein = 10F,
            ),
            goalConsumption = MockNutritionModel(
                fat = 10F,
                carb = 12F,
                ccal = 2000F,
                protein = 10F,
            )
        ))
    }

    private fun setupTitle() {
        binding.title.setup(
            model = TextModel(
                textValue = "Demonstration Fragment",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextSample() {
        binding.textSample.setup(
            model = TextModel(
                textValue = "Text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupSlideableTextSample() {
        binding.slideableTextSample.setup(
            model = TextModel(
                textValue = "Very very long text sample component",
                textColor = Color.WHITE,
                backgroundColor = Color.BLACK,
            )
        )
    }

    private fun setupTextIconButtonSample() {
        binding.textIconButtonSample.setup(
            model = ButtonModel(
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
            model = ButtonModel(
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
            model = ButtonModel(
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