package com.example.components.texts.common

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseText(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {

    abstract fun setup(model: BaseTextModel)
}