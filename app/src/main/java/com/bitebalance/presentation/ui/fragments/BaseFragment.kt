package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.components.databinding.NoItemsLayoutBinding
import com.ui.components.databinding.ToolbarBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    protected val themeVm by sharedViewModel<ThemeViewModel>()
    protected val navigationVm by sharedViewModel<NavigationViewModel>()

    protected lateinit var binding: T
    protected lateinit var toolbarBinding: ToolbarBinding
    protected lateinit var noItemsLayoutBinding: NoItemsLayoutBinding

    protected abstract fun onStartFragment(): View?

    protected abstract fun onStopFragment()

    protected abstract fun setupViewModelsObservation()

    protected open fun onResumeFragment() { setupViewModelsObservation() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return onStartFragment()
    }

    override fun onResume() {
        super.onResume()
        return onResumeFragment()
    }

    override fun onStop() {
        super.onStop()
        return onStopFragment()
    }
}