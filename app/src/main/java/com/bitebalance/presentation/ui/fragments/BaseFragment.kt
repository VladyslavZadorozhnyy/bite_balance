package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import android.graphics.Color
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ui.components.databinding.ToolbarBinding
import com.ui.components.databinding.NoItemsLayoutBinding
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.bitebalance.presentation.ui.activites.MainActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    protected val themeVm by sharedViewModel<ThemeViewModel>()
    protected val navigationVm by sharedViewModel<NavigationViewModel>()

    protected lateinit var binding: T
    protected lateinit var toolbarBinding: ToolbarBinding
    protected lateinit var noItemsBinding: NoItemsLayoutBinding

    protected val mActivity by lazy { requireActivity() as MainActivity }

    protected val primaryColor
        get() = themeVm.state.value?.primaryColor ?: Color.BLACK
    protected val secondaryColor
        get() = themeVm.state.value?.secondaryColor ?: Color.WHITE

    protected abstract fun onStartFragment(): View?

    protected abstract fun setupViewModelsObservation()

    protected open fun onResumeFragment() {
        binding.root.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_AUTO
        setupViewModelsObservation()
    }

    protected open fun onStopFragment() {
        binding.root.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
        themeVm.state.removeObservers(this)
        navigationVm.state.removeObservers(this)
    }

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