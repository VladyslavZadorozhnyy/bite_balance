package com.ui.components.progress.carousel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ui.common.ComponentUiConstants
import com.ui.components.R
import com.ui.components.progress.indicator.ProgressIndicator
import com.ui.components.progress.indicator.ProgressIndicatorModelNew

class CarouselAdapterNew(
    private val context: Context,
    private val model: ProgressCarouselModelNew,
) : PagerAdapter() {
    private val kcalName = context.getString(R.string.kcal_name)
    private val protsName = context.getString(R.string.prots_name)
    private val fatsName = context.getString(R.string.fats_name)
    private val carbsName = context.getString(R.string.carbs_name)

    private val kcalLabel = context.getString(R.string.kcal_label)
    private val gramLabel = context.getString(R.string.grams_label)

    override fun getCount(): Int { return ComponentUiConstants.CAROUSEL_SIZE }

    override fun isViewFromObject(view: View, `object`: Any): Boolean { return view == `object` }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val newItem = ProgressIndicator(context)
        bindView(newItem, position, container)

        return newItem
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (`object` as? ProgressIndicator)?.let { container.removeView(`object`) }
    }

    private fun bindView(view: ProgressIndicator, position: Int, container: ViewGroup) {
        val progressModel = when (position) {
            0 -> ProgressIndicatorModelNew(
                consumed = model.goalConsumption.kcals / 100f,
                goalConsumption = model.consumed.kcals,
                indicatorLabel = kcalLabel,
                indicatorName = kcalName,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor
            )
            1 -> ProgressIndicatorModelNew(
                consumed = model.goalConsumption.prots / 100f,
                goalConsumption = model.consumed.prots,
                indicatorLabel = gramLabel,
                indicatorName = protsName,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor
            )
            2 -> ProgressIndicatorModelNew(
                consumed = model.goalConsumption.fats / 100f,
                goalConsumption = model.consumed.fats,
                indicatorLabel = gramLabel,
                indicatorName = fatsName,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor
            )
            else -> ProgressIndicatorModelNew(
                consumed = model.goalConsumption.carbs / 100f,
                goalConsumption = model.consumed.carbs,
                indicatorLabel = gramLabel,
                indicatorName = carbsName,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor
            )
        }

        view.apply {
            setup(progressModel)
            container.addView(this)
        }
    }
}