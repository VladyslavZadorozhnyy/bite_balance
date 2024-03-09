package com.ui.basic.nav_bar

import com.ui.components.R
import com.ui.common.Constants
import com.ui.common.BaseUiComponentModel

data class NavigationBarModel(
    val navIcons: List<Int>,
    val foregroundColor: Int,
    val backgroundColor: Int,
    val onItemSelected: (itemId: Int) -> Unit
) : BaseUiComponentModel(
    componentType = Constants.ComponentUiType.NavBar
) {
    fun chosenIdToIndex(chosenId: Int): Int {
        return when (chosenId) {
            R.id.nav_home -> navIcons[0]
            R.id.nav_stats -> navIcons[1]
            R.id.nav_menu -> navIcons[2]
            else -> navIcons[3]
        }
    }
}