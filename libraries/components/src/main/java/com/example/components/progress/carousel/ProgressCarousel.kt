package com.example.components.progress.carousel

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.view.children
import androidx.core.view.doOnLayout
import androidx.core.view.updateMargins
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.components.R
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentUtils
import com.example.components.databinding.ProgressCarouselBinding
import com.example.components.texts.common.TextModel

class ProgressCarousel(
    context: Context,
    attrs: AttributeSet? = null
) : BaseComponent(context, attrs) {
    private val dotSize = ComponentUtils.dpToPx(context, 12)
    private val dotStartMargin = ComponentUtils.dpToPx(context, 12)

    private val dotLayoutParams = LinearLayout.LayoutParams(dotSize, dotSize).also {
        it.updateMargins(left = dotStartMargin)
    }

    private val binding by lazy {
        ProgressCarouselBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseComponentModel) {
        setupTitle()
        setupCarousel(model)
        updateDots(activePosition = 0)
    }

    private fun setupTitle() {
        binding.title.setup(
            model = TextModel(
                textValue = "Eeaten today:",
                textSize = 30,
                textColor = Color.BLACK,
                backgroundColor = Color.WHITE,
                backgroundRes = R.drawable.progress_title_shape
            )
        )

        backgroundTintList = getColorStateList(context, R.color.black)
    }

    private fun setupCarousel(model: BaseComponentModel) {
        (model as? ProgressCarouselModel)?.let { progressModel ->
            binding.viewPager.apply {
                adapter = CarouselAdapter(context, progressModel)
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageSelected(position: Int) { updateDots(position) }

                    override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}

                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
        }
    }

    private fun setupDots() {
        val nonActiveDotRes = R.drawable.non_active_dot_icon

        for (i in 0 until CarouselConstants.CAROUSEL_SIZE) {
            ImageView(context).apply {
                setImageDrawable(getDrawable(context, nonActiveDotRes))
                binding.sliderDots.addView(this, dotLayoutParams)
            }
        }
    }

    private fun updateDots(activePosition: Int) {
        if (binding.sliderDots.childCount == 0) { setupDots() }

        val activeDotRes = R.drawable.active_dot_icon
        val nonActiveDotRes = R.drawable.non_active_dot_icon

        for (i in 0 until binding.sliderDots.childCount) {
            val currentDotRes = if (i == activePosition) activeDotRes else nonActiveDotRes
            (binding.sliderDots.getChildAt(i) as? ImageView)?.setImageResource(currentDotRes)
        }
    }
}