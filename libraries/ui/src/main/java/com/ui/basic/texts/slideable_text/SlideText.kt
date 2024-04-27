package com.ui.basic.texts.slideable_text

import com.ui.components.R
import android.view.Gravity
import android.graphics.Paint
import android.text.TextUtils
import android.content.Context
import com.ui.common.Constants
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.databinding.TextBinding
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat.getFont

class SlideText(
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

        binding.background = getDrawable(context, R.drawable.text_shape)
        binding.background.setTint(model.backgroundColor)
        binding.marqueeRepeatLimit = -1

        binding.ellipsize = TextUtils.TruncateAt.MARQUEE
        binding.isSelected = true
        binding.isSingleLine = true
    }

    fun strikeThrough() {
        binding.paintFlags = binding.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun clearThrough() {
        binding.paintFlags = binding.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}