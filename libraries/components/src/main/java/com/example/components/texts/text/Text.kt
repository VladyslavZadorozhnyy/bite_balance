package com.example.components.texts.text

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.components.R
import com.example.components.common.ComponentConstants
import com.example.components.databinding.TextBinding
import com.example.components.texts.common.BaseText
import com.example.components.texts.common.BaseTextModel
import com.example.components.texts.common.TextConstants

class Text(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseText(context, attrs) {
    private val binding by lazy {
        TextBinding.inflate(LayoutInflater.from(context), this).textView
    }

    override fun setup(model: BaseTextModel) {
        binding.text = model.textValue
        binding.textSize = model.textSize.toFloat()
        binding.setTextColor(model.textColor)
        binding.typeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)

        binding.gravity = Gravity.CENTER
        binding.paint.style = Paint.Style.STROKE
        binding.paint.strokeWidth = ComponentConstants.TEXT_STROKE_WIDTH

        binding.background = ContextCompat.getDrawable(context, R.drawable.text_background)
        binding.background.setTint(model.backgroundColor)
    }
}