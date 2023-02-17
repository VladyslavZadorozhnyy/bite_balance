package com.example.components.checkbox

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.CheckboxBinding
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import com.example.components.R

class Checkbox(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseComponent(context, attrs) {
    private val tickIcon = getDrawable(context, R.drawable.tick_icon)
    private val crossIcon = getDrawable(context, R.drawable.cross_icon)

    private val binding by lazy {
        CheckboxBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseComponentModel) {

        (model as? CheckBoxModel)?.let { it ->
            binding.checkbox.backgroundTintList = getColorStateList(context, R.color.black)

            if (it.active) {
                binding.imageView.backgroundTintList = getColorStateList(context, R.color.white)

                if (it.checked) {
                    binding.imageView.setImageDrawable(tickIcon)
                    it.onChecked()
                }
            } else {
                binding.imageView.backgroundTintList = getColorStateList(context, R.color.gray)
                binding.imageView.setImageDrawable(crossIcon)
            }

            binding.imageView.setOnClickListener { (it as ImageView)
                if (it.drawable == tickIcon) {
                    binding.imageView.setImageDrawable(null)
                    model.onUnchecked()
                } else if (it.drawable == null) {
                    binding.imageView.setImageDrawable(tickIcon)
                    model.onChecked()
                }
            }
        }
    }
}