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

    override fun onResumeFragment() {
        super.onResumeFragment()
        binding.navigationComponent.onResume()
    }

    override fun setupViewModelsObservation() {
        binding.navigationComponent.setup(
            NavigationBarModel(
                navIcons = Constants.NAVIGATION_ICONS_LIST,
                backgroundColor = secondaryColor,
                foregroundColor = primaryColor,
            ) { chosenItemId ->
                val nextFragment = when (chosenItemId) {
                    R.id.nav_home -> HomeFragment.newInstance()
                    R.id.nav_stats -> StatsFragment.newInstance()
                    R.id.nav_menu -> MenuFragment.newInstance(creatingNewMeal = false)
                    else -> SettingsFragment.newInstance()
                }

                val currFrIdx = fragmentToIndex(mActivity.getCurrentFragment())
                val nxtFrIdx = fragmentToIndex(nextFragment)
                changeFragment(nextFragment, currFrIdx > nxtFrIdx)
            },
        )
    }

    private fun changeFragment(fragment: Fragment, leftToRightDirection: Boolean) {
        val enterAnim: Int = if (leftToRightDirection) R.anim.slide_right else R.anim.slide_left_2
        val exitAnim: Int = if (leftToRightDirection) R.anim.slide_right_2 else R.anim.slide_left
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            setCustomAnimations(enterAnim, exitAnim)
            replace(binding.contentFragment.id, fragment)
            commit()
        }
    }

    private fun fragmentToIndex(fragment: Fragment?): Int {
        return when (fragment) {
            is HomeFragment -> 0
            is StatsFragment -> 1
            is MenuFragment -> 2
            is SettingsFragment -> 3
            else -> -1
        }
    }

    fun updateNavBarColors() {
        binding.navigationComponent.updateBackgroundColor(secondaryColor)
        binding.navigationComponent.updateForegroundColor(primaryColor)
    }

    companion object {
        fun newInstance(): NavigationFragment {
            return NavigationFragment()
        }
    }
}