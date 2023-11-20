package com.ui.basic.recycler_views.metric_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding


class MetricRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? MetricRecyclerModel)?.let { recyclerModel ->
            binding.recyclerView.apply {
                adapter = MetricAdapter(
                    recyclerModel.items,
                    foregroundColor = recyclerModel.foregroundColor,
                    backgroundColor = recyclerModel.backgroundColor,
                )
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    fun getInputValues(): List<String> {
        return (binding.recyclerView.adapter as? MetricAdapter)?.getInputValues() ?: listOf()
    }
}