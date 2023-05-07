package com.bitebalance.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.bitebalance.databinding.FragmentProgressScreenBinding
import com.ui.components.R

class ProgressScreenFragment : Fragment() {
    private val binding by lazy {
        FragmentProgressScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.sublayoutContainer.backgroundTintList = getColorStateList(requireContext(), R.color.white)

        return binding.root
    }
}