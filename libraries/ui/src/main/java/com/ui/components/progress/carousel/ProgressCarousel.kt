package com.ui.components.progress.carousel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.view.updateMargins
import androidx.viewpager.widget.ViewPager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiConstants
import com.ui.common.ComponentUiUtils
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.ProgressCarouselBinding

class ProgressCarousel(
    context: Context,
    attrs: AttributeSet? = null
) : BaseUiComponent(context, attrs) {
    private val dotSize = ComponentUiUtils.dpToPx(context, 12)
    private val dotStartMargin = ComponentUiUtils.dpToPx(context, 12)

    private val dotLayoutParams = LinearLayout.LayoutParams(dotSize, dotSize).also {
        it.updateMargins(left = dotStartMargin)
    }

    private val binding by lazy {
        ProgressCarouselBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        setupTitle()
        setupCarousel(model)
        updateDots(activePosition = 0)
    }

    private fun setupTitle() {
        val titleValue = context.getString(R.string.eaten_today)

        binding.title.setup(
            model = TextModel(
                textValue = titleValue,
                textSize = 30,
                textColorRes = R.color.black,
                backgroundColor = R.color.white,
                backgroundRes = R.drawable.progress_title_shape,
            )
        )

        backgroundTintList = getColorStateList(context, R.color.black)
    }

    private fun setupCarousel(model: BaseUiComponentModel) {
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

        for (i in 0 until ComponentUiConstants.CAROUSEL_SIZE) {
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