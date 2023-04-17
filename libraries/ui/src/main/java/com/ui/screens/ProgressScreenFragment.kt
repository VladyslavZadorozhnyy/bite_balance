package com.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import com.ui.components.R
import com.ui.components.databinding.FragmentProgressScreenBinding

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