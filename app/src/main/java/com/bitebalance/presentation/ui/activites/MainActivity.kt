package com.bitebalance.presentation.ui.activites

import android.os.Bundle
import com.ui.components.R
import com.bitebalance.common.NavigationAction
import androidx.appcompat.app.AppCompatActivity
import com.bitebalance.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import com.bitebalance.presentation.ui.fragments.NavigationFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navigationVm by viewModel<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationObserving()
        setContentView(binding.root)
    }

    private fun setupNavigationObserving() {
        navigationVm.state.observe(this) { state ->
            supportFragmentManager.fragments.lastOrNull()?.onPause()
            val transaction = supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_to_up, R.anim.slide_to_down, R.anim.slide_to_up, R.anim.slide_to_down)

            when (state.action) {
                NavigationAction.POP ->
                    onBackPressed()
                NavigationAction.REPLACE ->
                    state.fragment?.let { transaction.replace(binding.fragmentContainer.id, it) }
                else ->
                    state.fragment?.let {
                        transaction.add(binding.fragmentContainer.id, it).addToBackStack(null) }
            }
            supportFragmentManager.fragments.lastOrNull()?.onStop()
            transaction.commit()
        }
    }

    fun updateNavBarColors() {
        supportFragmentManager.fragments.forEach { backStackedFragment ->
            (backStackedFragment as? NavigationFragment)?.updateNavBarColors()
        }
    }

    fun backPressUntil(componentName: String) {
        while (supportFragmentManager.fragments.lastOrNull()?.javaClass?.name != componentName)
            onBackPressed()
    }

    override fun onBackPressed() {
        onBackPressedDispatcher.onBackPressed()
        supportFragmentManager.fragments.lastOrNull()?.onResume()
        supportFragmentManager.fragments.forEach { (it as? NavigationFragment)?.onResume() }
    }
}