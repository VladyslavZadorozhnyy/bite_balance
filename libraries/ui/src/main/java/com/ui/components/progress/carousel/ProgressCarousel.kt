package com.ui.components.progress.carousel

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.updateMargins
import androidx.viewpager.widget.ViewPager
import com.ui.basic.texts.common.TextModelNew2
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiConstants
import com.ui.common.ComponentUiUtils
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
        setupTitle(model)
        setupCarousel(model)
        updateDots(activePosition = 0, model)
    }

    private fun setupTitle(model: BaseUiComponentModel) {
        val titleValue = context.getString(R.string.eaten_today)
        val textModelShape = context.getDrawable(R.drawable.progress_title_shape)
        val wrappedDrawable = DrawableCompat.wrap(textModelShape!!)


        (model as? ProgressCarouselModelNew)?.let {
            DrawableCompat.setTint(wrappedDrawable, model.secondaryColor)

            binding.title.setup(
                model = TextModelNew2(
                    textValue = titleValue,
                    textSize = 30,
                    textColor = model.primaryColor,
                    backgroundColor = model.secondaryColor,
                    backgroundRes = wrappedDrawable,
                )
            )
            backgroundTintList = ColorStateList.valueOf(model.primaryColor)
        }
    }

    private fun setupCarousel(model: BaseUiComponentModel) {
        (model as? ProgressCarouselModel)?.let { progressModel ->
            binding.viewPager.apply {
                adapter = CarouselAdapter(context, progressModel)
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageSelected(position: Int) { updateDots(position, model) }

                    override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}

                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
        }

        (model as? ProgressCarouselModelNew)?.let { progressModel ->
            binding.viewPager.apply {
                adapter = CarouselAdapterNew(context, progressModel)
                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageSelected(position: Int) { updateDots(position, progressModel) }

                    override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}

                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupDots(model: BaseUiComponentModel) {
        (model as? ProgressCarouselModelNew)?.let { progressModel ->
            val nonActiveDotRes = R.drawable.non_active_dot_icon
            val wrappedDrawable = DrawableCompat.wrap(context.getDrawable(nonActiveDotRes)!!)
            DrawableCompat.setTint(wrappedDrawable, progressModel.secondaryColor)

            for (i in 0 until ComponentUiConstants.CAROUSEL_SIZE) {
                ImageView(context).apply {
                    setImageDrawable(getDrawable(context, nonActiveDotRes))
                    binding.sliderDots.addView(this, dotLayoutParams)
                }
            }
        }
    }

    private fun updateDots(activePosition: Int, model: BaseUiComponentModel) {
        (model as? ProgressCarouselModelNew)?.let { progressModel ->
            if (binding.sliderDots.childCount == 0) { setupDots(model) }

            val activeDotRes = R.drawable.active_dot_icon
            val nonActiveDotRes = R.drawable.non_active_dot_icon

            val wrappedDrawable = DrawableCompat.wrap(context.getDrawable(nonActiveDotRes)!!)
            val wrappedDrawable2 = DrawableCompat.wrap(context.getDrawable(activeDotRes)!!)
            (wrappedDrawable2 as? GradientDrawable)?.let {
                it.mutate()
                it.setStroke(10, progressModel.secondaryColor)
            }

            DrawableCompat.setTint(wrappedDrawable, progressModel.secondaryColor)
            //DrawableCompat.setTint(wrappedDrawable2, progressModel.secondaryColor)

            for (i in 0 until binding.sliderDots.childCount) {
                val currentDotRes = if (i == activePosition) wrappedDrawable2 else wrappedDrawable
                (binding.sliderDots.getChildAt(i) as? ImageView)?.setImageDrawable(currentDotRes)
            }
        }
    }
}