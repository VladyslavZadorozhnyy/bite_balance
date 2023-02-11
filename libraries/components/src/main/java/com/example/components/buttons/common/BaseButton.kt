package com.example.components.buttons.common

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseButton(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    abstract fun setup(model: BaseButtonModel)
}