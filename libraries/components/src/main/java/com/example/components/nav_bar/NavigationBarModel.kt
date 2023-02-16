package com.example.components.nav_bar

import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants

data class NavigationBarModel(
    val nonActiveIconsRes: List<Int>,
    val activeIconsRes: List<Int>,
    val onItemSelected: (itemId: Int) -> Unit
) : BaseComponentModel(
    componentType = ComponentConstants.COMPONENT_TYPE_NAV_BAR
)