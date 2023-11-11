package com.ui.components.dialogs.yes_no_dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.common.BaseUiComponentModel
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.YesNoDialogBinding
import com.ui.components.dialogs.common.BaseDialogModelNew

class YesNoDialog(
    activity: Activity,
    private val model: BaseUiComponentModel
) : Dialog(activity) {
    private val transparentBackground = ColorDrawable(getColor(context, R.color.transparent))

    private val binding by lazy {
        YesNoDialogBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    @SuppressLint("ResourceAsColor")
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

            binding.yesButton.setup(
                ButtonModelNew(
                    labelTextRes = R.string.yes,
                    labelTextSize = 15,
                    foregroundColor = model.backgroundColor,
                    backgroundColor = model.textColor,
                    onClickListener = {
                        model.onPositiveClicked()
                        dismiss()
                    }
                )
            )

            binding.noButton.setup(
                ButtonModelNew(
                    labelTextRes = R.string.no,
                    labelTextSize = 15,
                    foregroundColor = model.backgroundColor,
                    backgroundColor = model.textColor,
                    onClickListener = {
                        model.onNegativeClicked()
                        dismiss()
                    }
                )
            )

            binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
            binding.layout.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
        }
    }
}