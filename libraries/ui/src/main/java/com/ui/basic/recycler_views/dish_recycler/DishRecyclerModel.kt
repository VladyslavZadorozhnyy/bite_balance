package com.ui.basic.recycler_views.dish_recycler

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType
import com.ui.model.DishModel

data class DishRecyclerModel(
    val items: List<DishModel>,
    val onClickListener: (DishModel) -> Unit
) : BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)