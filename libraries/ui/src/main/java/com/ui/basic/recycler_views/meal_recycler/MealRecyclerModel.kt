package com.ui.basic.recycler_views.meal_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockMealModel

data class MealRecyclerModel(
    val items: List<MockMealModel>,
    val backgroundColorRes: Int,
    val onClickListener: (MockMealModel) -> Unit
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)