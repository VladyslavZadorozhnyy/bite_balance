package com.example.components.progress.carousel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.updateMargins
import androidx.viewpager.widget.PagerAdapter
import com.example.components.R
import com.example.components.common.ComponentUtils
import com.example.components.databinding.ProgressCarouselBinding
import com.example.components.mocks.MockNutritionModel
import com.example.components.progress.indicator.ProgressIndicator
import com.example.components.progress.indicator.ProgressIndicatorModel

class CarouselAdapter(
    private val context: Context,
    private val model: ProgressCarouselModel,
) : PagerAdapter() {

    override fun getCount(): Int { return CarouselConstants.CAROUSEL_SIZE }

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
        if (position == 0) {
            view.setup(
                model = ProgressIndicatorModel(model.consumed.ccal, model.goalConsumption.ccal,
                    CarouselConstants.CCAL_LABEL, CarouselConstants.CCAL_NAME)
            )
        } else if (position == 1) {
            view.setup(
                model = ProgressIndicatorModel(model.consumed.protein, model.goalConsumption.protein,
                    CarouselConstants.PROT_LABEL, CarouselConstants.PROT_NAME
                )
            )
        } else if (position == 2) {
            view.setup(
                model = ProgressIndicatorModel(model.consumed.fat, model.goalConsumption.fat,
                    CarouselConstants.FATS_LABEL, CarouselConstants.FATS_NAME
                )
            )
        } else {
            view.setup(
                model = ProgressIndicatorModel(model.consumed.carb, model.goalConsumption.carb,
                    CarouselConstants.CARB_LABEL, CarouselConstants.CARB_NAME
                )
            )
        }

        container.addView(view)
    }
}