package com.example.components.progress.indicator

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.ProgressIndicatorBinding
import com.example.components.texts.common.TextModel

class ProgressIndicator(
    context: Context,
    attrs: AttributeSet? = null
) : BaseComponent(context, attrs) {
    private val binding by lazy {
        ProgressIndicatorBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseComponentModel) {
        (model as? ProgressIndicatorModel)?.let {
            binding.layout.backgroundTintList = ColorStateList.valueOf(Color.BLACK)

            setupAndGetProgress(it.consumed, it.goalConsumption).also { progress ->
                setupDynamicLabels(model, progress)
                setupStaticLabels(model)
            }
        }
    }

    private fun setupAndGetProgress(consumedValue: Float?, goalValue: Float?): Int {
        if (consumedValue == null || goalValue == null) {
            binding.progressBar.progress = 0
            return 0
        }

        val curProgress = (consumedValue / goalValue * 100).toInt()
        binding.progressBar.progress = minOf(curProgress, 100)
        binding.progressBar.setIndicatorColor(IndicatorUtils.progressToColor(curProgress))

        return curProgress
    }

    private fun setupDynamicLabels(model: ProgressIndicatorModel, progress: Int) {
        val consumedLabel = IndicatorUtils.valueToLabel(model.consumed)
        val goalLabel = IndicatorUtils.valueToLabel(model.goalConsumption)
        val fromLimitLabel = IndicatorUtils.progressToLabel(progress)

        binding.progressLabel.setup(
            model = TextModel(
                textValue = "$consumedLabel ${model.indicatorLabel} \n out of $goalLabel",
                textSize = 25,
                textColor = Color.WHITE,
                backgroundColor = Color.TRANSPARENT
            )
        )

        binding.progressValueLabel.setup(
            model = TextModel(
                textValue = fromLimitLabel,
                textSize = 20,
                textColor = Color.WHITE,
                backgroundColor = IndicatorUtils.progressToColor(progress)
            )
        )
    }

    private fun setupStaticLabels(model: ProgressIndicatorModel) {
        binding.progressTitleLabel.setup(
            model = TextModel(
                textValue = "From limit:",
                textSize = 20,
                textColor = Color.WHITE,
                backgroundColor = Color.TRANSPARENT
            )
        )

        binding.indicatorTitle.setup(
            model = TextModel(
                textValue = model.indicatorName,
                textSize = 30,
                textColor = Color.WHITE,
                backgroundColor = Color.TRANSPARENT
            )
        )
    }
}