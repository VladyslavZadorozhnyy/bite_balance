package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentNavigationBinding
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.nav_bar.NavigationBarModel
import com.ui.common.Constants
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NavigationFragment : Fragment() {
    private val binding by lazy { FragmentNavigationBinding.inflate(layoutInflater) }
    private val themeViewModel by sharedViewModel<ThemeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupNavigationComponent()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.navigationComponent.onResume()
    }

    fun updateNavBarColors() {
        binding.navigationComponent.updateBackgroundColor(themeViewModel.state.value!!.secondaryColor)
        binding.navigationComponent.updateForegroundColor(themeViewModel.state.value!!.primaryColor)
    }

    private fun setupNavigationComponent() {
        binding.navigationComponent.setup(
            NavigationBarModel(
                navIcons = Constants.NAVIGATION_ICONS_LIST,
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
            ) { chosenItemId ->
                val nextFragment = when (chosenItemId) {
                    R.id.nav_home -> HomeScreenFragment()
                    R.id.nav_stats -> StatsScreenFragment()
                    R.id.nav_menu -> MenuScreenFragment.newInstance(creatingNewMeal = false)
                    else -> SettingsScreenFragment()
                }
                changeFragment(nextFragment)
            }
        )
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
            replace(binding.contentFragment.id, fragment)
            commit()
        }
    }
}