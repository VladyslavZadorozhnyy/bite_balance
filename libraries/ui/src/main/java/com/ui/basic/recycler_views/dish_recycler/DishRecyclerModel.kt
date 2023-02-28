package com.ui.basic.recycler_views.dish_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.mocks.MockDishModel

data class DishRecyclerModel(
    val items: List<MockDishModel>
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)