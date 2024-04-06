package com.ui.model

class MetricModel(
    val name: String,
    val hint: String = "",
    val suffix: String = "",
    val editable: Boolean = false,
    val onlyNumbers: Boolean = false,
)