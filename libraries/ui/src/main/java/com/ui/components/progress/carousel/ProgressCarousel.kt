package com.ui.components.progress.carousel

import com.ui.components.R
import android.content.Context
import com.ui.common.Constants
import android.widget.ImageView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ui.common.BaseUiComponent
import com.ui.common.ComponentUiUtils
import android.annotation.SuppressLint
import androidx.core.view.updateMargins
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import androidx.viewpager.widget.ViewPager
import android.graphics.drawable.GradientDrawable
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.content.ContextCompat.getDrawable
import com.ui.basic.texts.common.TextModel
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


        (model as? ProgressCarouselModel)?.let {
            DrawableCompat.setTint(wrappedDrawable, model.secondaryColor)

            binding.title.setup(
                model = TextModel(
                    textValue = titleValue,
                    textSize = 30,
                    textColor = model.primaryColor,
                    backgroundColor = model.secondaryColor,
                    backgroundResDrawable = wrappedDrawable,
                    isSingleLine = true,
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
                    override fun onPageSelected(position: Int) { updateDots(position, progressModel) }

                    override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}

                    override fun onPageScrollStateChanged(state: Int) {}
                })
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupDots(model: BaseUiComponentModel) {
        (model as? ProgressCarouselModel)?.let { progressModel ->
            val nonActiveDotRes = R.drawable.non_active_dot_icon
            val wrappedDrawable = DrawableCompat.wrap(context.getDrawable(nonActiveDotRes)!!)
            DrawableCompat.setTint(wrappedDrawable, progressModel.secondaryColor)

            for (i in 0 until Constants.CAROUSEL_SIZE) {
                ImageView(context).apply {
                    setImageDrawable(getDrawable(context, nonActiveDotRes))
                    binding.sliderDots.addView(this, dotLayoutParams)
                }
            }
        }
    }

    private fun updateDots(activePosition: Int, model: BaseUiComponentModel) {
        (model as? ProgressCarouselModel)?.let { progressModel ->
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