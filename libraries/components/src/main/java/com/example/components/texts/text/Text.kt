package com.example.components.texts.text

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.components.R
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.databinding.TextBinding
import com.example.components.texts.common.TextModel

class Text(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseComponent(context, attrs) {
    private val binding by lazy {
        TextBinding.inflate(LayoutInflater.from(context), this).textView
    }

    override fun setup(model: BaseComponentModel) {
        (model as? TextModel)?.let {
            binding.text = model.textValue
            binding.textSize = model.textSize.toFloat()
            binding.setTextColor(model.textColor)
            binding.typeface = ResourcesCompat.getFont(context, R.font.ultra_regular_font)

            binding.gravity = Gravity.CENTER
            binding.paint.style = Paint.Style.STROKE
            binding.paint.strokeWidth = ComponentConstants.TEXT_STROKE_WIDTH

            binding.background = if (it.backgroundRes != null) {
                ContextCompat.getDrawable(context, it.backgroundRes)
            } else {
                ContextCompat.getDrawable(context, R.drawable.text_shape)
            }

            binding.background.setTint(model.backgroundColor)
        }
    }

    fun strikeThrough() {
        binding.paintFlags = binding.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun unstrikeThrough() {
        binding.paintFlags = binding.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}