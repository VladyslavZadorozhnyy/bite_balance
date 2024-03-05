package com.ui.components.dialogs.yes_no_dialog

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
import android.graphics.drawable.ColorDrawable
import com.ui.basic.buttons.common.ButtonModel
import androidx.core.content.ContextCompat.getDrawable
import com.ui.components.dialogs.common.BaseDialogModel
import com.ui.components.databinding.YesNoDialogBinding

class YesNoDialog(
    activity: Activity,
    private val model: BaseUiComponentModel
) : Dialog(activity) {
    private val binding by lazy { YesNoDialogBinding.inflate(LayoutInflater.from(context)) }
    private val transparentBackground by lazy { ColorDrawable(Color.TRANSPARENT) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    fun setup() {
        if (model !is BaseDialogModel) return

        window?.setBackgroundDrawable(transparentBackground)
        binding.title.setup(
            model = TextModel(
                textSize = Constants.TEXT_SIZE,
                textValue = model.title,
                textColor = model.textColor,
                backgroundColor = model.backgroundColor,
            ),
        )
        binding.yesButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.yes,
                labelTextSize = Constants.TEXT_SIZE_SMALL,
                foregroundColor = model.backgroundColor,
                backgroundColor = model.textColor,
                onClickListener = {
                    model.onPositiveClicked()
                    dismiss()
                },
            ),
        )
        binding.noButton.setup(
            model = ButtonModel(
                labelTextRes = R.string.no,
                labelTextSize = Constants.TEXT_SIZE_SMALL,
                foregroundColor = model.backgroundColor,
                backgroundColor = model.textColor,
                onClickListener = {
                    model.onNegativeClicked()
                    dismiss()
                },
            ),
        )
        binding.layout.background = getDrawable(context, R.drawable.dialog_shape)
        binding.layout.backgroundTintList = ColorStateList.valueOf(model.backgroundColor)
    }
}