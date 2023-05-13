package com.ui.basic.recycler_views.text_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding

class TextRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {

    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? TextRecyclerModel)?.let { recyclerModel ->
            binding.recyclerView.apply {
                adapter = TextAdapter(recyclerModel.items, model.onClickListener)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}