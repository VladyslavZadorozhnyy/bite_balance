package com.example.bitebalance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bitebalance.databinding.ActivityMainBinding
import com.example.components.fragments.DemonstrationFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        Move to separate component later
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, DemonstrationFragment())
            addToBackStack(null)
            commit()
        }
    }
}