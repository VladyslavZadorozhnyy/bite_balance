package com.ui.components.progress.indicator

import com.ui.components.R
import android.graphics.Color
import android.content.Context
import com.ui.common.Constants
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import com.ui.common.BaseUiComponentModel
import com.ui.basic.texts.common.TextModel
import androidx.core.content.ContextCompat.getColor
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
        if (model !is ProgressIndicatorModel) return

        binding.layout.backgroundTintList = ColorStateList.valueOf(model.primaryColor)
        setupAndGetProgress(model.consumed, model.goalConsumption).also { progress ->
            setupDynamicLabels(model, progress)
            setupStaticLabels(model)
        }
    }

    private fun setupAndGetProgress(
        consumedValue: Float?,
        goalValue: Float?,
    ): Int {
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

    private fun setupDynamicLabels(
        model: ProgressIndicatorModel,
        progress: Int,
    ) {
        val consumedLabel = IndicatorUtils.valueToLabel(model.consumed)
        val goalLabel = IndicatorUtils.valueToLabel(model.goalConsumption)
        val fromLimitLabel = IndicatorUtils.progressToLabel(progress)
        val progressLabel = "$consumedLabel ${model.indicatorLabel} \n $outOfLabel $goalLabel"

        binding.progressLabel.setup(
            model = TextModel(
                textValue = progressLabel,
                textSize = Constants.TEXT_SIZE_MD,
                backgroundColor = model.primaryColor,
                textColor = model.secondaryColor,
                isSingleLine = false,
            )
        )
        binding.progressValueLabel.setup(
            model = TextModel(
                textValue = fromLimitLabel,
                textSize = Constants.TEXT_SIZE,
                textColor = Color.WHITE,
                backgroundColor = getColor(context, IndicatorUtils.progressToColor(progress))
            )
        )
    }

    private fun setupStaticLabels(model: ProgressIndicatorModel) {
        binding.progressTitleLabel.setup(
            model = TextModel(
                textValue = context.getString(R.string.from_limit),
                textSize = Constants.TEXT_SIZE,
                textColor = model.secondaryColor,
                backgroundColor = Color.TRANSPARENT
            )
        )
        binding.indicatorTitle.setup(
            model = TextModel(
                textValue = model.indicatorName,
                textSize = Constants.TEXT_SIZE_BIG,
                textColor = model.secondaryColor,
                backgroundColor = Color.TRANSPARENT
            )
        )
    }
}