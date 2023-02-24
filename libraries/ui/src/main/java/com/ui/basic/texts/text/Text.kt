package com.ui.basic.texts.text

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.res.ResourcesCompat.getFont
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiConstants
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.TextBinding

class Text(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        TextBinding.inflate(LayoutInflater.from(context), this).textView
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? TextModel)?.let {
            binding.text = model.textValue
            binding.textSize = model.textSize.toFloat()
            binding.setTextColor(getColor(context, model.textColorRes))
            binding.typeface = getFont(context, R.font.ultra_regular_font)

            binding.gravity = Gravity.CENTER
            binding.paint.style = Paint.Style.STROKE
            binding.paint.strokeWidth = ComponentUiConstants.TEXT_STROKE_WIDTH

            binding.background = if (it.backgroundRes != null) {
                getDrawable(context, it.backgroundRes)
            } else {
                getDrawable(context, R.drawable.text_shape)
            }
            binding.background.setTint(getColor(context, model.backgroundColor))
        }
    }

    fun strikeThrough() {
        binding.paintFlags = binding.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun unstrikeThrough() {
        binding.paintFlags = binding.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}