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

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, HomeScreenFragment())
//            commit()
//        }

//        supportFragmentManager.beginTransaction().apply {
//            replace(binding.fragmentContainer.id, NavigationFragment())
//            commit()
//        }

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
}