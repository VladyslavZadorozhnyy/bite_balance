package com.bitebalance.presentation.ui.fragments

import android.view.View
import com.ui.components.R
import com.ui.common.Constants
import androidx.fragment.app.Fragment
import com.ui.basic.nav_bar.NavigationBarModel
import com.bitebalance.databinding.FragmentNavigationBinding

class NavigationFragment : BaseFragment<FragmentNavigationBinding>() {

    override fun onStartFragment(): View {
        binding = FragmentNavigationBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onStopFragment() {
//        TODO("Not yet implemented") AAADIP REMOVE
    }

    override fun onResumeFragment() {
        super.onResumeFragment()
        binding.navigationComponent.onResume()
    }

    override fun setupViewModelsObservation() {
        binding.navigationComponent.setup(
            NavigationBarModel(
                navIcons = Constants.NAVIGATION_ICONS_LIST,
                backgroundColor = themeVm.state.value!!.secondaryColor,
                foregroundColor = themeVm.state.value!!.primaryColor,
            ) { chosenItemId ->
                val nextFragment = when (chosenItemId) {
                    R.id.nav_home -> HomeFragment.newInstance()
                    R.id.nav_stats -> StatsFragment.newInstance()
                    R.id.nav_menu -> MenuFragment.newInstance(creatingNewMeal = false)
                    else -> SettingsFragment.newInstance()
                }
                changeFragment(nextFragment)
            },
        )
    }

    private fun changeFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
            replace(binding.contentFragment.id, fragment)
            commit()
        }
    }

    fun updateNavBarColors() {
        binding.navigationComponent.updateBackgroundColor(themeVm.state.value!!.secondaryColor)
        binding.navigationComponent.updateForegroundColor(themeVm.state.value!!.primaryColor)
    }

    companion object {
        fun newInstance(): NavigationFragment {
            return NavigationFragment()
        }
    }
}