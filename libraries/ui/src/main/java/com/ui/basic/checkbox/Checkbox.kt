package com.ui.basic.checkbox

import com.ui.components.R
import android.graphics.Color
import android.content.Context
import android.widget.ImageView
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import androidx.core.graphics.BlendModeCompat
import com.ui.components.databinding.CheckboxBinding
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.BlendModeColorFilterCompat

class Checkbox(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val tickIcon = getDrawable(context, R.drawable.tick_icon)
    private val crossIcon = getDrawable(context, R.drawable.cross_icon)

    private val binding by lazy {
        CheckboxBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is CheckBoxModel) return

        tickIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            model.backgroundColor, BlendModeCompat.SRC_ATOP)
        crossIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
            model.backgroundColor, BlendModeCompat.SRC_ATOP)
        binding.checkboxStroke.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
        binding.checkbox.backgroundTintList = ColorStateList.valueOf(model.foregroundColor)

        if (model.active) {
            if (model.checked) {
                binding.imageView.setImageDrawable(tickIcon)
                model.onChecked()
            }
        } else {
            binding.imageView.setImageDrawable(if (model.checked) tickIcon else crossIcon)
            binding.checkbox.backgroundTintList = ColorStateList.valueOf(Color.DKGRAY)
            return
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