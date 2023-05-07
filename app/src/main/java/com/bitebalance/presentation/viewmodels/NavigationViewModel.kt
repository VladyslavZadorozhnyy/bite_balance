package com.bitebalance.presentation.viewmodels

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitebalance.common.NavigationAction
import com.bitebalance.presentation.states.NavigationState

class NavigationViewModel : ViewModel() {
    private val _state = MutableLiveData(NavigationState())
    val state: LiveData<NavigationState> = _state

    fun popScreen() {
        val curFragment = _state.value?.fragment
        _state.value = NavigationState(curFragment, NavigationAction.POP)
    }

    fun navigateTo(
        nextFragment: Fragment,
        navAction: NavigationAction = NavigationAction.ADD,
        args: Bundle? = null,
    ) {
        val curFragment = _state.value?.fragment

        if (curFragment != nextFragment) {
            nextFragment.arguments = args
            _state.value = NavigationState(nextFragment, navAction)
        }
    }
}