package com.example.components.buttons.icon_button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import com.example.components.buttons.common.ButtonModel
import com.example.components.buttons.common.ButtonConstants
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.IconButtonBinding

class IconButton(context: Context, attrs: AttributeSet? = null) : BaseComponent(context, attrs) {
    private val binding by lazy {
        IconButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseComponentModel) {
        (model as? ButtonModel)?.let {
            binding.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
            binding.strokeColor = getColorStateList(context, model.foregroundColorRes)
            binding.iconTint = getColorStateList(context, model.foregroundColorRes)
            binding.cornerRadius = ButtonConstants.CORNER_RADIUS
            binding.strokeWidth = model.strokeWidth
            binding.iconSize = model.iconSize

            model.iconRes?.let { binding.setIconResource(it) }
            model.onClickListener?.let { binding.setOnClickListener(it) }
        }
    }
}