package com.ui.basic.recycler_views.instruction_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding

class InstructionRecycler(
    context: Context,
    attrs: AttributeSet? = null,
): BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? InstructionRecyclerModel)?.let { recyclerModel ->
            binding.recyclerView.apply {
                setBackgroundColor(recyclerModel.backgroundColor)
                adapter = InstructionAdapter(recyclerModel.items, recyclerModel.backgroundColor, model.foregroundColor)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}