package com.ui.basic.recycler_views.meal_recycler

import com.ui.common.Constants
import com.ui.model.MealModelUnboxed
import com.ui.common.BaseUiComponentModel

data class MealRecyclerModel(
    val items: List<MealModelUnboxed>,
    val backgroundColor: Int,
    val foregroundColor: Int,
    val onClickListener: (MealModelUnboxed) -> Unit,
    val onSwipeListener: (MealModelUnboxed) -> Unit,
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)