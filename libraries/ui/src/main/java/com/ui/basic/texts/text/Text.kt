package com.ui.basic.texts.text

import com.ui.components.R
import android.view.Gravity
import android.text.TextUtils
import android.graphics.Paint
import android.content.Context
import com.ui.common.Constants
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.databinding.TextBinding
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat.getFont

class Text(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        TextBinding.inflate(LayoutInflater.from(context), this).textView
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is TextModel) return

        binding.text = model.textValue
        binding.textSize = model.textSize.toFloat()
        binding.typeface = getFont(context, R.font.ultra_regular_font)
        binding.setTextColor(model.textColor)

        binding.gravity = Gravity.CENTER
        binding.paint.style = Paint.Style.STROKE
        binding.paint.strokeWidth = Constants.TEXT_STROKE_WIDTH

        if (model.backgroundResDrawable == null) {
            binding.background = getDrawable(context, R.drawable.text_shape)
            binding.background.setTint(model.backgroundColor)
        } else if (model.isSingleLine) {
            binding.background = model.backgroundResDrawable
            binding.marqueeRepeatLimit = -1
            binding.isSingleLine = true
            binding.ellipsize = TextUtils.TruncateAt.MARQUEE
        } else {
            val viewBackground = model.backgroundResDrawable
            val wrappedDrawable = DrawableCompat.wrap(viewBackground)
            DrawableCompat.setTint(wrappedDrawable, model.backgroundColor)
            binding.background = viewBackground
        }
        binding.isSelected = true
    }

    fun strikeThrough() {
        binding.paintFlags = binding.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun clearThrough() {
        binding.paintFlags = binding.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}