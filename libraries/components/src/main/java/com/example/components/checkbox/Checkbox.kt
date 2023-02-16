package com.example.components.checkbox

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.CheckboxBinding
import com.example.components.databinding.TextBinding

class Checkbox(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseComponent(context, attrs) {
    private val binding by lazy {
        CheckboxBinding.inflate(LayoutInflater.from(context), this).checkbox
    }

    override fun setup(model: BaseComponentModel) {
        binding.setBackgroundColor(Color.BLUE)
        Log.d("AAADIP", "Checkbox setup() called")
    }
}