package com.ui.common

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseUiComponent(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs) {
    abstract fun setup(model: BaseUiComponentModel)
    open fun onResume() {}
}