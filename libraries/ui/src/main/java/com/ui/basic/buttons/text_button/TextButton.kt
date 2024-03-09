package com.ui.basic.buttons.text_button

import com.ui.components.R
import android.graphics.Paint
import android.content.Context
import com.ui.common.Constants
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import com.ui.basic.buttons.common.ButtonModel
import com.ui.components.databinding.TextButtonBinding
import androidx.core.content.res.ResourcesCompat.getFont

class TextButton(context: Context, attrs: AttributeSet? = null) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        TextButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is ButtonModel) return

        binding.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
        binding.strokeColor = ColorStateList.valueOf(model.foregroundColor)
        binding.cornerRadius = Constants.CORNER_RADIUS
        binding.strokeWidth = model.strokeWidth

        model.labelTextRes?.let {
            binding.text = context.getString(it)
            binding.typeface = getFont(context, R.font.ultra_regular_font)
            binding.paint.style = Paint.Style.STROKE
            binding.paint.strokeWidth = Constants.TEXT_STROKE_WIDTH
            binding.setTextColor(ColorStateList.valueOf(model.foregroundColor))
            binding.textSize = model.labelTextSize.toFloat()
        }
        model.onClickListener?.let { binding.setOnClickListener(it) }
    }
}