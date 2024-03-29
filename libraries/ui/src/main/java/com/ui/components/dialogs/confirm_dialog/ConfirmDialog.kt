package com.ui.components.dialogs.confirm_dialog

import android.app.Activity
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.ContextCompat.getColorStateList
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.common.BaseUiComponentModel
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.ConfirmDialogBinding
import com.ui.components.dialogs.common.BaseDialogModelNew

class ConfirmDialog(
    activity: Activity,
    private val model: BaseUiComponentModel
) : Dialog(activity) {
    private val binding by lazy { ConfirmDialogBinding.inflate(LayoutInflater.from(context)) }

    private val transparentBackground = ColorDrawable(getColor(context, R.color.transparent))

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
                    textColorRes = model.textColorRes,
                    backgroundColor = model.backgroundColorRes
                )
            )

            binding.confirmButton.setup(
                ButtonModel(
                    labelTextRes = model.buttonTextRes,
                    labelTextSize = 10,
                    foregroundColorRes = model.backgroundColorRes,
                    backgroundColorRes = R.color.black,
                    onClickListener = {
                        model.onConfirmClicked()
                        dismiss()
                    }
                )
            )

            binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList = getColorStateList(context, model.backgroundColorRes)
        }

        (model as? BaseDialogModelNew)?.let {
            window?.setBackgroundDrawable(transparentBackground)

            binding.title.setup(
                TextModelNew(
                    textSize = 20,
                    textValue = model.title,
                    textColor = model.textColor,
                    backgroundColor = model.backgroundColor
                )
            )

            binding.confirmButton.setup(
                ButtonModelNew(
                    labelTextRes = model.buttonText,
                    labelTextSize = 10,
                    foregroundColor = model.backgroundColor,
                    backgroundColor = model.textColor,
                    onClickListener = {
                        model.onConfirmClicked()
                        dismiss()
                    }
                )
            )

            binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
        }
    }
}