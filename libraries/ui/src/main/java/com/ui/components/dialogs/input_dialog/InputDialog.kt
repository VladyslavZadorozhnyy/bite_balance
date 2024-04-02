package com.ui.components.dialogs.input_dialog

import android.os.Bundle
import android.app.Dialog
import com.ui.components.R
import android.app.Activity
import android.graphics.Color
import com.ui.common.Constants
import android.view.LayoutInflater
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.buttons.common.ButtonModel
import android.graphics.drawable.ColorDrawable
import androidx.core.content.ContextCompat.getDrawable
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.databinding.InputDialogBinding

class InputDialog(
    activity: Activity,
    private val model: BaseUiComponentModel
) : Dialog(activity) {
    private val binding by lazy { InputDialogBinding.inflate(LayoutInflater.from(context)) }
    private val transparentBackground by lazy { ColorDrawable(Color.TRANSPARENT) }
    private var inputValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
    }

    fun setup() {
        if (model !is BaseDialogModel) return

        window?.setBackgroundDrawable(transparentBackground)
        binding.title.setup(
            model = TextModel(
                textSize = Constants.TEXT_SIZE_BIG,
                textValue = model.title,
                textColor = model.textColor,
                backgroundColor = model.backgroundColor,
            ),
        )
        binding.inputForm.setup(
            model = InputFormModel(
                active = true,
                onInputChange = { inputValue = it },
                backgroundColor = model.backgroundColor,
                foregroundColor = model.textColor,
            ),
        )
        binding.confirmButton.setup(
            model = ButtonModel(
                labelTextRes = model.buttonText,
                labelTextSize = Constants.TEXT_SIZE_SMALL,
                foregroundColor = model.backgroundColor,
                backgroundColor = model.textColor,
                onClickListener = {
                    model.onInputConfirmed(inputValue)
                    dismiss()
                },
            ),
        )
        binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
        binding.layout.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
    }
}