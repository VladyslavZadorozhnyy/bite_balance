package com.ui.basic.input_form

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.core.widget.addTextChangedListener
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.ComponentUiConstants
import com.ui.components.R
import com.ui.components.databinding.InputFormBinding

class InputForm (
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val activeBackgroundRes = getDrawable(context, R.drawable.input_form_active_shape)
    private val inactiveBackgroundRes = getDrawable(context, R.drawable.input_form_not_active_shape)

    private val blackColor = getColorStateList(context, R.color.black)
    private val grayColor = getColorStateList(context, R.color.gray)
    private val whiteColor = getColorStateList(context, R.color.white)

    private val binding by lazy {
        InputFormBinding.inflate(LayoutInflater.from(context), this).form
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? InputFormModel)?.let {
            binding.typeface = getFont(context, R.font.ultra_regular_font)
            binding.paint.style = Paint.Style.STROKE
            binding.paint.strokeWidth = ComponentUiConstants.TEXT_STROKE_WIDTH

            binding.setTextColor(blackColor)
            binding.hint = it.hint

            if (it.active) {
                binding.background = activeBackgroundRes

                binding.setHintTextColor(grayColor)
                binding.addTextChangedListener {
                    model.onInputChange.invoke(it?.toString() ?: "")
                }
            } else {
                binding.background = inactiveBackgroundRes
                binding.setHintTextColor(whiteColor)
            }
        }
    }

}