package com.example.components.dialogs.confirm_dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.*
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.ConfirmDialogBinding
import com.example.components.dialogs.common.BaseDialogModel
import com.example.components.texts.common.TextModel

class ConfirmDialog(
    activity: Activity,
    private val model: BaseComponentModel
) : Dialog(activity) {
    private val transparentBackground = ColorDrawable(Color.TRANSPARENT)

    val binding by lazy {
        ConfirmDialogBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    fun setup() {
        (model as? BaseDialogModel)?.let {
            window?.setBackgroundDrawable(transparentBackground)

            binding.title.setup(
                TextModel(
                    textSize = 20,
                    textValue = model.title,
                    textColor = model.textColor,
                    backgroundColor = getColor(context, model.backgroundColorRes)
                )
            )

            binding.confirmButton.setup(
                ButtonModel(
                    labelTextRes = R.string.text_button_sample,
                    labelTextSize = 10,
                    foregroundColorRes = model.backgroundColorRes,
                    backgroundColorRes = R.color.black,
                    onClickListener = {
                        Log.d("AAADIP", "Confirm button clicked")
                        dismiss()
                    }
                )
            )

            binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
        }
    }
}