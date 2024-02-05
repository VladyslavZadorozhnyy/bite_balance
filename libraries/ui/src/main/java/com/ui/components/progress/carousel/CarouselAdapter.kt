package com.ui.components.progress.carousel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ui.common.Constants
import com.ui.components.R
import com.ui.components.progress.indicator.ProgressIndicator
import com.ui.components.progress.indicator.ProgressIndicatorModel

class CarouselAdapter(
    private val context: Context,
    private val model: ProgressCarouselModel,
) : PagerAdapter() {
    private val kcalName = context.getString(R.string.kcal_name)
    private val protsName = context.getString(R.string.prots_name)
    private val fatsName = context.getString(R.string.fats_name)
    private val carbsName = context.getString(R.string.carbs_name)

    private val kcalLabel = context.getString(R.string.kcal_label)
    private val gramLabel = context.getString(R.string.grams_label)

    override fun getCount(): Int { return Constants.CAROUSEL_SIZE }

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
            0 -> ProgressIndicatorModel(
                consumed = model.goalConsumption.kcals / 100f,
                goalConsumption = model.consumed.kcals,
                indicatorLabel = kcalLabel,
                indicatorName = kcalName,
            )
            1 -> ProgressIndicatorModel(
                consumed = model.goalConsumption.prots / 100f,
                goalConsumption = model.consumed.prots,
                indicatorLabel = gramLabel,
                indicatorName = protsName,
            )
            2 -> ProgressIndicatorModel(
                consumed = model.goalConsumption.fats / 100f,
                goalConsumption = model.consumed.fats,
                indicatorLabel = gramLabel,
                indicatorName = fatsName,
            )
            else -> ProgressIndicatorModel(
                consumed = model.goalConsumption.carbs / 100f,
                goalConsumption = model.consumed.carbs,
                indicatorLabel = gramLabel,
                indicatorName = carbsName,
            )
        }

        view.apply {
            setup(progressModel)
            container.addView(this)
        }
    }
}