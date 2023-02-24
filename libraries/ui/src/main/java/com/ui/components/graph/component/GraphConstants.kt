package com.ui.components.graph.component

import android.content.Context
import com.ui.components.R

object GraphConstants {

    fun getSpinnerItems(context: Context): List<String> {
        return listOf(
            context.getString(R.string.kcal_consumption),
            context.getString(R.string.prots_consumption),
            context.getString(R.string.fats_consumption),
            context.getString(R.string.carbs_consumption)
        )
    }
}