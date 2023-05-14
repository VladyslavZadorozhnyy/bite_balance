package com.bitebalance.presentation.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ui.components.R
import com.bitebalance.common.NavigationAction
import com.bitebalance.databinding.ActivityMainBinding
import com.bitebalance.presentation.viewmodels.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navigationVm by viewModel<NavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationObserving()
        setContentView(binding.root)

//        TODO: Move to separate component later
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, DemonstrationFragment())
//            addToBackStack(null)
//            commit()
//        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, HomeScreenFragment())
//            commit()
//        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, NavigationFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, InfoScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, ProgressScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, AddMealScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, StatsScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MenuScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SettingsScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, TodaysMealsScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, CreateNewScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, ChooseDishScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MyGoalsScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, DishScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, AppearanceScreenFragment())
//            commit()
//        }
//
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, TextScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SupportFeedbackScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SupportFeedbackScreenFragment())
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MealDetailsScreenFragment())
//            commit()
//        }
    }

    private fun setupNavigationObserving() {
        navigationVm.state.observe(this) { state ->
            val transaction = supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.slide_in, R.anim.slide_out)

            when (state.action) {
                NavigationAction.POP ->
                    supportFragmentManager.fragments.lastOrNull()?.let {
                        transaction.remove(it) }
                NavigationAction.REPLACE ->
                    state.fragment?.let {
                        transaction.replace(binding.fragmentContainer.id, it) }
                else ->
                    state.fragment?.let {
                        transaction.add(binding.fragmentContainer.id, it).addToBackStack(null) }
            }

            transaction.commit()
        }
    }
}