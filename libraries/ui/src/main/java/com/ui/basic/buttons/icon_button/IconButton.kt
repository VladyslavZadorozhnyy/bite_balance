package com.ui.basic.buttons.icon_button

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonConstants
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.IconButtonBinding

class IconButton(context: Context, attrs: AttributeSet? = null) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        IconButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseUiComponentModel) {
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