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
import androidx.core.view.updateMargins
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import androidx.viewpager.widget.ViewPager
import com.ui.basic.texts.common.TextModel
import android.graphics.drawable.GradientDrawable
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat.wrap
import com.ui.components.databinding.ProgressCarouselBinding


class ProgressCarousel(
    context: Context,
    attrs: AttributeSet? = null
) : BaseUiComponent(context, attrs) {
    private val dotSize = ComponentUiUtils.dpToPx(context, Constants.DOT_SIZE_DP)
    private val dotStartMargin = ComponentUiUtils.dpToPx(context, Constants.DOT_SIZE_DP)

    private val dotLayoutParams = LinearLayout.LayoutParams(dotSize, dotSize).apply {
        updateMargins(left = dotStartMargin)
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
        val textShape = AppCompatResources.getDrawable(context, R.drawable.progress_title_shape)
        val wrappedDrawable = DrawableCompat.wrap(textShape!!)

        if (model !is ProgressCarouselModel) return

        DrawableCompat.setTint(wrappedDrawable, model.secondaryColor)
        binding.title.setup(
            model = TextModel(
                textValue = titleValue,
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = model.primaryColor,
                backgroundColor = model.secondaryColor,
                backgroundResDrawable = wrappedDrawable,
                isSingleLine = true,
            )
        )
        backgroundTintList = ColorStateList.valueOf(model.primaryColor)
    }

    private fun setupCarousel(model: BaseUiComponentModel) {
        if (model !is ProgressCarouselModel) return

        binding.viewPager.apply {
            adapter = CarouselAdapter(context, model)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(position: Int) { updateDots(position, model) }

                override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}

                override fun onPageScrollStateChanged(state: Int) {}
            })
        }
    }

    private fun setupDots(model: BaseUiComponentModel) {
        if (model !is ProgressCarouselModel) return

        val nonActiveDotDr = getDrawable(context, R.drawable.non_active_dot_icon) ?: return
        val wrappedDrawable = wrap(nonActiveDotDr)
        DrawableCompat.setTint(wrappedDrawable, model.secondaryColor)

        for (i in 0 until Constants.CAROUSEL_SIZE) {
            ImageView(context).apply {
                setImageDrawable(nonActiveDotDr)
                binding.sliderDots.addView(this, dotLayoutParams)
            }
        }
    }

    private fun updateDots(activePosition: Int, model: BaseUiComponentModel) {
        (model as? ProgressCarouselModel)?.let { progressModel ->
            if (binding.sliderDots.childCount == 0) { setupDots(model) }

            val activeDotRes = R.drawable.active_dot_icon
            val nonActiveDotRes = R.drawable.non_active_dot_icon

            val wrappedDrawable = wrap(AppCompatResources.getDrawable(context, nonActiveDotRes)!!)
            val wrappedDrawable2 = wrap(AppCompatResources.getDrawable(context, activeDotRes)!!)
            (wrappedDrawable2 as? GradientDrawable)?.let {
                it.mutate()
                it.setStroke(Constants.DOT_STROKE_PX, progressModel.secondaryColor)
            }

            DrawableCompat.setTint(wrappedDrawable, progressModel.secondaryColor)
            for (i in 0 until binding.sliderDots.childCount) {
                val currentDotRes = if (i == activePosition) wrappedDrawable2 else wrappedDrawable
                (binding.sliderDots.getChildAt(i) as? ImageView)?.setImageDrawable(currentDotRes)
            }
        }
    }
}