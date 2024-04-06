package com.bitebalance.presentation.states

import androidx.fragment.app.Fragment
import com.bitebalance.common.NavigationAction
import com.bitebalance.presentation.ui.fragments.NavigationFragment

data class NavigationState(
    val fragment: Fragment? = NavigationFragment.newInstance(),
    val action: NavigationAction = NavigationAction.REPLACE,
)