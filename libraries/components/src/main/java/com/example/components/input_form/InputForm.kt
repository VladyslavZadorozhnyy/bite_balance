package com.example.components.input_form

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.core.widget.addTextChangedListener
import com.example.components.R
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.common.ComponentConstants
import com.example.components.databinding.InputFormBinding

class InputForm (
    context: Context,
    attrs: AttributeSet? = null,
) : BaseComponent(context, attrs) {
    private val activeBackgroundRes = getDrawable(context, R.drawable.input_form_active_shape)
    private val unactiveBackgroundRes = getDrawable(context, R.drawable.input_form_not_active_shape)

    private val blackColor = getColorStateList(context, R.color.black)
    private val grayColor = getColorStateList(context, R.color.gray)
    private val whiteColor = getColorStateList(context, R.color.white)

    private val binding by lazy {
        InputFormBinding.inflate(LayoutInflater.from(context), this).form
    }

    override fun setup(model: BaseComponentModel) {
        (model as? InputFormModel)?.let {
            binding.typeface = getFont(context, R.font.ultra_regular_font)
            binding.paint.style = Paint.Style.STROKE
            binding.paint.strokeWidth = ComponentConstants.TEXT_STROKE_WIDTH

            binding.setTextColor(blackColor)
            binding.hint = it.hint

            if (it.active) {
                binding.background = activeBackgroundRes

                binding.setHintTextColor(grayColor)
                binding.addTextChangedListener {
                    model.onInputChange.invoke(it?.toString() ?: "")
                }
            } else {
                binding.isFocusable = false
                binding.background = unactiveBackgroundRes
                binding.setHintTextColor(whiteColor)
            }
        }
    }

}