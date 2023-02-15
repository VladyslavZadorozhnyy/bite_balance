package com.example.components.buttons.text_button

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.res.ResourcesCompat.getFont
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.buttons.common.ButtonConstants
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.databinding.TextButtonBinding

class TextButton(context: Context, attrs: AttributeSet? = null) : BaseComponent(context, attrs) {
    private val binding by lazy {
        TextButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseComponentModel) {
        (model as? ButtonModel)?.let {
            binding.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
            binding.strokeColor = getColorStateList(context, model.foregroundColorRes)

            binding.cornerRadius = ButtonConstants.CORNER_RADIUS
            binding.strokeWidth = model.strokeWidth

            model.labelTextRes?.let {
                binding.text = context.getString(it)
                binding.typeface = getFont(context, R.font.ultra_regular_font)
                binding.paint.style = Paint.Style.STROKE
                binding.paint.strokeWidth = ComponentConstants.TEXT_STROKE_WIDTH
                binding.setTextColor(getColorStateList(context, model.foregroundColorRes))

                binding.textSize = model.labelTextSize.toFloat()
            }

            model.onClickListener?.let { binding.setOnClickListener(it) }
        }
    }
}