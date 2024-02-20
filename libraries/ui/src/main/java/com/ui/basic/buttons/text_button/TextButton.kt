package com.ui.basic.buttons.text_button

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.res.ResourcesCompat.getFont
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.components.R
import com.ui.components.databinding.TextButtonBinding

class TextButton(context: Context, attrs: AttributeSet? = null) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        TextButtonBinding.inflate(LayoutInflater.from(context), this).buttonView
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? ButtonModel)?.let {
            binding.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
            binding.strokeColor = getColorStateList(context, model.foregroundColorRes)

            binding.cornerRadius = Constants.CORNER_RADIUS
            binding.strokeWidth = model.strokeWidth

            model.labelTextRes?.let {
                binding.text = context.getString(it)
                binding.typeface = getFont(context, R.font.ultra_regular_font)
                binding.paint.style = Paint.Style.STROKE
                binding.paint.strokeWidth = Constants.TEXT_STROKE_WIDTH
                binding.setTextColor(getColorStateList(context, model.foregroundColorRes))

                binding.textSize = model.labelTextSize.toFloat()
            }

            model.onClickListener?.let { binding.setOnClickListener(it) }
        }

        (model as? ButtonModelNew)?.let {
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
}