package com.ui.components.dialogs.yes_no_dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import com.ui.basic.buttons.common.ButtonModel
import com.ui.common.BaseUiComponentModel
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.YesNoDialogBinding

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

    fun setup() {
        (model as? BaseDialogModel)?.let {
            window?.setBackgroundDrawable(transparentBackground)

            binding.title.setup(
                TextModel(
                    textSize = 20,
                    textValue = model.title,
                    textColorRes = model.textColorRes,
                    backgroundColor = getColor(context, model.backgroundColorRes)
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
    }
}