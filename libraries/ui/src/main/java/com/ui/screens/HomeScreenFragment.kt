package com.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.FragmentHomeScreenBinding
import com.ui.components.databinding.RecyclerViewBinding
import com.ui.components.progress.carousel.ProgressCarouselModel
import com.ui.components.progress.indicator.ProgressIndicatorModel
import com.ui.mocks.MockNutritionModel

class HomeScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentHomeScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.greetings.setup(
            model = TextModel(
                textValue = "Good morning",
                textSize = 35,
                textColorRes = R.color.black,
                backgroundColor = R.color.white
            )
        )

        binding.progressCarousel.setup(
            model = ProgressCarouselModel(
                consumed = MockNutritionModel(
                    fat = 10F,
                    carb = 14F,
                    kcal = 750F,
                    protein = 10F,
                ),
                goalConsumption = MockNutritionModel(
                    fat = 10F,
                    carb = 12F,
                    kcal = 2000F,
                    protein = 10F,
                )
            )
        )



        return binding.root
    }
}