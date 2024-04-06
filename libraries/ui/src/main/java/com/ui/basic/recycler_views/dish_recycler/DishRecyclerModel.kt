package com.ui.basic.recycler_views.dish_recycler

import com.ui.model.DishModel
import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

data class DishRecyclerModel(
    val items: List<DishModel>,
    val primaryColor: Int,
    val secondaryColor: Int,
    val onClickListener: (DishModel) -> Unit,
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler
)