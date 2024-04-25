package com.ui.basic.input_form

import com.ui.components.R
import android.graphics.Color
import android.graphics.Paint
import android.content.Context
import com.ui.common.Constants
import android.util.TypedValue
import android.text.InputType.*
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import androidx.core.widget.addTextChangedListener
import com.ui.components.databinding.InputFormBinding
import androidx.core.content.res.ResourcesCompat.getFont

class InputForm (
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        InputFormBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is InputFormModel) return

        binding.form.typeface = getFont(context, R.font.ultra_regular_font)
        binding.form.paint.style = Paint.Style.STROKE
        binding.form.paint.strokeWidth = Constants.TEXT_STROKE_WIDTH
        binding.form.setTextColor(model.foregroundColor)

        if (!model.active) {
            binding.formStroke.setBackgroundResource(R.drawable.input_form_not_active_shape)
            binding.formStroke.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
            binding.form.setBackgroundResource(R.drawable.input_form_not_active_shape)
            binding.form.backgroundTintList = ColorStateList.valueOf(Color.DKGRAY)

            binding.form.setHintTextColor(Color.DKGRAY)
        } else {
            binding.formStroke.setBackgroundResource(R.drawable.input_form_active_shape)
            binding.formStroke.backgroundTintList = ColorStateList.valueOf(model.foregroundColor)
            binding.form.setBackgroundResource(R.drawable.input_form_active_shape)
            binding.form.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)

            binding.form.setHintTextColor(model.foregroundColor)
            binding.form.addTextChangedListener {
                model.onInputChange.invoke(it?.toString() ?: "")
            }
        }
        binding.form.setText(model.hint)
        model.hintGravity?.let { binding.form.gravity = it }
        binding.form.setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.TEXT_SIZE.toFloat())
        binding.form.setPadding(10,5,0,0)
    }

    fun setupOnlyNumbers() {
        binding.form.inputType =
            TYPE_CLASS_NUMBER or TYPE_NUMBER_FLAG_DECIMAL or TYPE_NUMBER_FLAG_SIGNED
    }
}