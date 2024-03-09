package com.ui.basic.recycler_views.instruction_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.components.databinding.RecyclerViewBinding

class InstructionRecycler(
    context: Context,
    attrs: AttributeSet? = null,
): BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is InstructionRecyclerModel) return

        binding.recyclerView.apply {
            setBackgroundColor(model.backgroundColor)
            adapter = InstructionAdapter(
                items = model.items,
                backgroundColor = model.backgroundColor,
                foregroundColor = model.foregroundColor,
            )
            layoutManager = LinearLayoutManager(context)
        }
    }
}