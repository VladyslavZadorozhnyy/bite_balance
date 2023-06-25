package com.ui.basic.recycler_views.meal_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.MealModelUnboxed

data class MealRecyclerModel(
    val items: List<MealModelUnboxed>,
    val backgroundColorRes: Int,
    val onClickListener: (MealModelUnboxed) -> Unit,
    val onSwipeListener: (MealModelUnboxed) -> Unit,
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)