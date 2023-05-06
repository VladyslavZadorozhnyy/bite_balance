package com.bitebalance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bitebalance.databinding.ActivityMainBinding
import com.ui.mocks.DemonstrationFragment
import com.ui.screens.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        TODO: Move to separate component later
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, DemonstrationFragment())
//            addToBackStack(null)
//            commit()
//        }

        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, HomeScreenFragment())
            commit()
        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, NavigationFragment())
//            addToBackStack(null)
//            commit()
//        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, InfoScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, ProgressScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, AddMealScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, StatsScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MenuScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SettingsScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, TodaysMealsScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, CreateNewScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, ChooseDishScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MyGoalsScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, DishScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, AppearanceScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, TextScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SupportFeedbackScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
//
//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, SupportFeedbackScreenFragment())
//            addToBackStack(null)
//            commit()
//        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, MealDetailsScreenFragment())
//            addToBackStack(null)
//            commit()
//        }
    }
}