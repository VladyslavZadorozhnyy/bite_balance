package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitebalance.databinding.FragmentNavigationBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.ui.basic.nav_bar.NavigationBarModel
import com.ui.components.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NavigationFragment : Fragment() {
    private val binding by lazy {
        FragmentNavigationBinding.inflate(layoutInflater)
    }

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
                    R.drawable.nav_home_not_active,
                    R.drawable.nav_stats_not_active,
                    R.drawable.nav_menu_not_active,
                    R.drawable.nav_settings_not_active
                ),
                activeIconsRes = listOf(
                    R.drawable.nav_home_active,
                    R.drawable.nav_stats_active,
                    R.drawable.nav_menu_active,
                    R.drawable.nav_settings_active
                ),
            ) { chosenItemId ->
                val nextFragment = when (chosenItemId) {
                    R.id.nav_home -> HomeScreenFragment()
                    R.id.nav_stats -> StatsScreenFragment()
                    R.id.nav_menu -> MenuScreenFragment()
                    else -> SettingsScreenFragment()
                }
                activity?.supportFragmentManager?.beginTransaction()?.apply {
                    replace(binding.contentFragment.id, nextFragment)
                    commit()
                }
            }
        )
    }
}