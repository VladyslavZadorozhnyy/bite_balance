package com.ui.basic.checkbox

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.ui.components.R
import com.ui.components.databinding.CheckboxBinding

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

        (model as? CheckBoxModel)?.let { it ->
            tickIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                model.backgroundColor,
                BlendModeCompat.SRC_ATOP,
            )
            crossIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                model.backgroundColor,
                BlendModeCompat.SRC_ATOP,
            )
            binding.checkboxStroke.backgroundTintList = ColorStateList.valueOf(it.backgroundColor)
            binding.checkbox.backgroundTintList = ColorStateList.valueOf(it.foregroundColor)

            if (it.active) {
                if (it.checked) {
                    binding.imageView.setImageDrawable(tickIcon)
                    it.onChecked()
                }
            } else {
                if (it.checked) {
                    binding.imageView.setImageDrawable(tickIcon)
                } else {
                    binding.imageView.setImageDrawable(crossIcon)
                }
            }

//            TODO: Change later this condition
//            if (model.checked || !model.active) {
            if (!model.active) {
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
}