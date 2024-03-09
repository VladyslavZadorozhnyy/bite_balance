package com.ui.basic.recycler_views.text_recycler

import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel

data class TextRecyclerModel(
    val items: List<TextModel>,
    val onClickListener: (TextModel) -> Unit,
): BaseUiComponentModel(
    componentType = Constants.ComponentUiType.Recycler,
)