package com.ui.basic.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
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
            binding.checkbox.backgroundTintList = getColorStateList(context, R.color.black)

            if (it.active) {
                binding.imageView.backgroundTintList = getColorStateList(context, R.color.white)

                if (it.checked) {
                    binding.imageView.setImageDrawable(tickIcon)
                    it.onChecked()
                }
            } else {
                binding.imageView.backgroundTintList = getColorStateList(context, R.color.gray)

                if (it.checked) {
                    binding.imageView.setImageDrawable(tickIcon)
                } else {
                    binding.imageView.setImageDrawable(crossIcon)
                }
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