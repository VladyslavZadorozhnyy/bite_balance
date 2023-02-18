package com.example.components.dialogs.input_dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.InputDialogBinding
import com.example.components.dialogs.common.BaseDialogModel
import com.example.components.input_form.InputFormModel
import com.example.components.texts.common.TextModel

class InputDialog(
    activity: Activity,
    private val model: BaseComponentModel
) : Dialog(activity) {
    private var inputValue = ""
    private val transparentBackground = ColorDrawable(Color.TRANSPARENT)

    val binding by lazy {
        InputDialogBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
    }

    fun setup() {
        (model as? BaseDialogModel)?.let {
            window?.setBackgroundDrawable(transparentBackground)

            binding.title.setup(
                TextModel(
                    textSize = 30,
                    textValue = model.title,
                    textColor = model.textColor,
                    backgroundColor = ContextCompat.getColor(context, model.backgroundColorRes)
                )
            )

            binding.inputForm.setup(
                model = InputFormModel(
                    active = true,
                    onInputChange = { inputValue = it }
                )
            )

            binding.confirmButton.setup(
                ButtonModel(
                    labelTextRes = R.string.add,
                    labelTextSize = 15,
                    foregroundColorRes = model.backgroundColorRes,
                    backgroundColorRes = R.color.white,
                    onClickListener = {
                        model.onInputConfirmed(inputValue)
                        dismiss()
                    }
                )
            )

            binding.layout.background = ContextCompat.getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList =
                ContextCompat.getColorStateList(context, model.backgroundColorRes)
        }
    }
}