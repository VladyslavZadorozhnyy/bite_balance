package com.ui.basic.recycler_views.text_recycler

import com.ui.basic.texts.common.TextModel
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class TextRecyclerModel(
    val items: List<TextModel>,
    val onClickListener: (TextModel) -> Unit
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)