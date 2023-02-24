package com.ui.components.dialogs.input_dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getColorStateList
import com.ui.basic.buttons.common.ButtonModel
import com.ui.common.BaseUiComponentModel
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.InputDialogBinding

class InputDialog(
    activity: Activity,
    private val model: BaseUiComponentModel
) : Dialog(activity) {
    private var inputValue = ""
    private val transparentBackground = ColorDrawable(getColor(context, R.color.transparent))

    private val binding by lazy {
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
                    textColorRes = model.textColorRes,
                    backgroundColor = getColor(context, model.backgroundColorRes)
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

            binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
        }
    }
}