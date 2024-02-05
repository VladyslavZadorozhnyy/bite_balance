package com.ui.basic.input_form

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.text.InputType
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat.getFont
import androidx.core.widget.addTextChangedListener
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.common.Constants
import com.ui.components.R
import com.ui.components.databinding.InputFormBinding

class InputForm (
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        InputFormBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? InputFormModel)?.let {
            binding.form.typeface = getFont(context, R.font.ultra_regular_font)
            binding.form.paint.style = Paint.Style.STROKE
            binding.form.paint.strokeWidth = Constants.TEXT_STROKE_WIDTH

            binding.form.setTextColor(model.foregroundColor)
            binding.form.hint = it.hint

            if (it.active && it.hint.equals("*Message to developers*")) {
                binding.formStroke.setBackgroundResource(R.drawable.input_form_active_shape)
                binding.formStroke.backgroundTintList = ColorStateList.valueOf(model.foregroundColor)

                binding.form.setBackgroundResource(R.drawable.input_form_active_shape)
                binding.form.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)

                binding.form.setHintTextColor(model.foregroundColor)
                binding.form.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                binding.form.gravity = Gravity.CENTER;

                binding.form.addTextChangedListener {
                    model.onInputChange.invoke(it?.toString() ?: "")
                }
            } else if (it.active) {
                binding.formStroke.setBackgroundResource(R.drawable.input_form_active_shape)
                binding.formStroke.backgroundTintList = ColorStateList.valueOf(model.foregroundColor)

                binding.form.setBackgroundResource(R.drawable.input_form_active_shape)
                binding.form.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)

                binding.form.setHintTextColor(Color.DKGRAY)
                binding.form.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                binding.form.setPadding(0,5,0,0)

                binding.form.addTextChangedListener {
                    model.onInputChange.invoke(it?.toString() ?: "")
                }
            } else {
                binding.form.setHintTextColor(Color.WHITE)

                binding.formStroke.setBackgroundResource(R.drawable.input_form_not_active_shape)
                binding.formStroke.backgroundTintList = ColorStateList.valueOf(Color.BLACK)

                binding.form.setBackgroundResource(R.drawable.input_form_not_active_shape)
                binding.form.backgroundTintList = ColorStateList.valueOf(Color.DKGRAY)

                binding.form.setHintTextColor(Color.DKGRAY)
                binding.form.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
                binding.form.setPadding(0,5,0,0)
            }
        }
    }

    fun setupOnlyNumbers() {
        binding.form.inputType =
            InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
    }
}