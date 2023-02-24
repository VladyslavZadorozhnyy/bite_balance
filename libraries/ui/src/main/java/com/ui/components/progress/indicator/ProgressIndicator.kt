package com.ui.components.progress.indicator

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getColor
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel
import com.ui.components.R
import com.ui.components.databinding.ProgressIndicatorBinding

class ProgressIndicator(
    context: Context,
    attrs: AttributeSet? = null
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        ProgressIndicatorBinding.inflate(LayoutInflater.from(context), this)
    }
    private val outOfLabel = context.getString(R.string.out_of)

    override fun setup(model: BaseUiComponentModel) {
        (model as? ProgressIndicatorModel)?.let {
            binding.layout.backgroundTintList = getColorStateList(context, R.color.black)

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
        val indicatorColor = getColor(context, IndicatorUtils.progressToColor(curProgress))

        binding.progressBar.progress = minOf(curProgress, 100)
        binding.progressBar.setIndicatorColor(indicatorColor)
        return curProgress
    }

    private fun setupDynamicLabels(model: ProgressIndicatorModel, progress: Int) {
        val consumedLabel = IndicatorUtils.valueToLabel(model.consumed)
        val goalLabel = IndicatorUtils.valueToLabel(model.goalConsumption)
        val fromLimitLabel = IndicatorUtils.progressToLabel(progress)
        val progressLabel = "$consumedLabel ${model.indicatorLabel} \n $outOfLabel $goalLabel"

        binding.progressLabel.setup(
            model = TextModel(
                textValue = progressLabel,
                textSize = 25,
            )
        )

        binding.progressValueLabel.setup(
            model = TextModel(
                textValue = fromLimitLabel,
                textSize = 20,
                backgroundColor = IndicatorUtils.progressToColor(progress)
            )
        )
    }

    private fun setupStaticLabels(model: ProgressIndicatorModel) {
        val progressTitleValue = context.getString(R.string.from_limit)

        binding.progressTitleLabel.setup(
            model = TextModel(
                textValue = progressTitleValue,
                textSize = 20,
                backgroundColor = R.color.transparent
            )
        )

        binding.indicatorTitle.setup(
            model = TextModel(
                textValue = model.indicatorName,
                textSize = 30,
                backgroundColor = R.color.transparent
            )
        )
    }
}