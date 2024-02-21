package com.ui.components.progress.indicator

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import com.ui.basic.texts.common.TextModelNew
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
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
        (model as? ProgressIndicatorModel)?.let { model ->
            binding.layout.backgroundTintList = ColorStateList.valueOf(model.primaryColor)
            setupAndGetProgress(model.consumed, model.goalConsumption).also { progress ->
                setupDynamicLabels(model, progress)
                setupStaticLabels(model)
            }
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
            model = TextModelNew(
                textValue = progressLabel,
                textSize = 25,
                backgroundColor = model.primaryColor,
                textColor = model.secondaryColor,
                isSingleLine = false,
            )
        )

        binding.progressValueLabel.setup(
            model = TextModelNew(
                textValue = fromLimitLabel,
                textSize = 20,
                textColor = Color.WHITE,
                backgroundColor = getColor(context, IndicatorUtils.progressToColor(progress))
            )
        )
    }

    private fun setupStaticLabels(model: ProgressIndicatorModel) {
        binding.progressTitleLabel.setup(
            model = TextModelNew(
                textValue = context.getString(R.string.from_limit),
                textSize = 20,
                textColor = model.secondaryColor,
                backgroundColor = Color.TRANSPARENT
            )
        )

        binding.indicatorTitle.setup(
            model = TextModelNew(
                textValue = model.indicatorName,
                textSize = 30,
                textColor = model.secondaryColor,
                backgroundColor = Color.TRANSPARENT
            )
        )
    }
}