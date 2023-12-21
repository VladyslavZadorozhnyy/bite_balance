package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentNavigationBinding
import com.bitebalance.presentation.viewmodels.ThemeViewModel
import com.ui.basic.nav_bar.NavigationBarModel
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

    private fun setupNavigationComponent() {
        binding.navigationComponent.setup(
            NavigationBarModel(
                nonActiveIconsRes = listOf(
                    R.drawable.nav_home_active,
                    R.drawable.nav_stats_active,
                    R.drawable.nav_menu_active,
                    R.drawable.nav_settings_active
                ),
                activeIconsRes = listOf(
                    R.drawable.nav_home_active,
                    R.drawable.nav_stats_active,
                    R.drawable.nav_menu_active,
                    R.drawable.nav_settings_active
                ),
                backgroundColor = themeViewModel.state.value!!.secondaryColor,
                foregroundColor = themeViewModel.state.value!!.primaryColor,
            ) { chosenItemId ->
                val nextFragment = when (chosenItemId) {
                    R.id.nav_home -> HomeScreenFragment()
                    R.id.nav_stats -> StatsScreenFragment()
                    R.id.nav_menu -> MenuScreenFragment.newInstance(creatingNewMeal = false)
                    else -> SettingsScreenFragment()
                }
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    replace(binding.contentFragment.id, nextFragment)
                    commit()
                }
            }
        )
    }
}