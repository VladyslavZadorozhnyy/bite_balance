package com.example.components.buttons.text_icon_button

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.buttons.common.ButtonConstants
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.databinding.TextIconButtonBinding

class TextIconButton(context: Context, attrs: AttributeSet? = null) : BaseComponent(context, attrs) {
    private val binding by lazy {
        TextIconButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseComponentModel) {
        (model as? ButtonModel)?.let {
            binding.backgroundTintList =
                ContextCompat.getColorStateList(context, model.backgroundColorRes)
            binding.strokeColor = ContextCompat.getColorStateList(context, model.foregroundColorRes)

            binding.cornerRadius = ButtonConstants.CORNER_RADIUS
            binding.strokeWidth = model.strokeWidth

            binding.iconTint = ContextCompat.getColorStateList(context, model.foregroundColorRes)
            model.iconRes?.let { binding.setIconResource(it) }
            binding.iconSize = model.iconSize

            model.labelTextRes?.let {
                binding.text = context.getString(it)
                binding.typeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)
                binding.paint.style = Paint.Style.STROKE
                binding.paint.strokeWidth = ComponentConstants.TEXT_STROKE_WIDTH
                binding.setTextColor(ContextCompat.getColorStateList(context, model.foregroundColorRes))

                binding.textSize = model.labelTextSize.toFloat()
            }

            model.onClickListener?.let { binding.setOnClickListener(it) }
        }
    }
}