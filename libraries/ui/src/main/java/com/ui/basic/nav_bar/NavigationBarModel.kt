package com.ui.basic.nav_bar

import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiType

data class NavigationBarModel(
    val nonActiveIconsRes: List<Int>,
    val activeIconsRes: List<Int>,
    val onItemSelected: (itemId: Int) -> Unit
) : BaseUiComponentModel(
    componentType = ComponentUiType.NavBar
)