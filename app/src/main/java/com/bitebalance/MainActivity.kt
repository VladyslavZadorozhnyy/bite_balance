package com.bitebalance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bitebalance.databinding.ActivityMainBinding
import com.ui.mocks.DemonstrationFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        TODO: Move to separate component later
        supportFragmentManager.beginTransaction().apply {
            replace(binding.fragmentContainer.id, DemonstrationFragment())
            addToBackStack(null)
            commit()
        }
    }
}