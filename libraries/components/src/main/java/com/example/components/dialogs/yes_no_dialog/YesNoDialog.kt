package com.example.components.dialogs.yes_no_dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.example.components.R
import com.example.components.buttons.common.ButtonModel
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.ConfirmDialogBinding
import com.example.components.databinding.YesNoDialogBinding
import com.example.components.dialogs.common.BaseDialogModel
import com.example.components.texts.common.TextModel

class YesNoDialog(
    activity: Activity,
    private val model: BaseComponentModel
) : Dialog(activity) {
    private val transparentBackground = ColorDrawable(Color.TRANSPARENT)

    val binding by lazy {
        YesNoDialogBinding.inflate(LayoutInflater.from(context))
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
                    backgroundColor = ContextCompat.getColor(context, model.backgroundColorRes)
                )
            )

            binding.yesButton.setup(
                ButtonModel(
                    labelTextRes = R.string.yes,
                    labelTextSize = 15,
                    foregroundColorRes = model.backgroundColorRes,
                    backgroundColorRes = R.color.black,
                    onClickListener = {
                        model.onPositiveClicked()
                        dismiss()
                    }
                )
            )

            binding.noButton.setup(
                ButtonModel(
                    labelTextRes = R.string.no,
                    labelTextSize = 15,
                    foregroundColorRes = model.backgroundColorRes,
                    backgroundColorRes = R.color.black,
                    onClickListener = {
                        model.onNegativeClicked()
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