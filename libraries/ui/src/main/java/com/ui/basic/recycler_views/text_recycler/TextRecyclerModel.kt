package com.ui.basic.recycler_views.text_recycler

import com.ui.basic.texts.common.TextModelNew
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class TextRecyclerModel(
    val items: List<TextModelNew>,
    val onClickListener: (TextModelNew) -> Unit
): BaseUiComponentModel(
    componentType = ComponentUiType.Recycler
)