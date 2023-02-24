package com.ui.components.progress.carousel

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.ui.common.ComponentUiConstants
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
            0 -> ProgressIndicatorModel(model.consumed.kcal, model.goalConsumption.kcal,
                kcalLabel, kcalName)
            1 -> ProgressIndicatorModel(model.consumed.protein, model.goalConsumption.protein,
                gramLabel, protsName)
            2 -> ProgressIndicatorModel(model.consumed.fat, model.goalConsumption.fat,
                gramLabel, fatsName)
            else -> ProgressIndicatorModel(model.consumed.carb, model.goalConsumption.carb,
                gramLabel, carbsName)
        }

        view.apply {
            setup(progressModel)
            container.addView(this)
        }
    }
}