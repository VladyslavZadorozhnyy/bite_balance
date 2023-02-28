package com.ui.basic.recycler_views.dish_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding

class DishRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? DishRecyclerModel)?.let { recyclerModel ->
            binding.recyclerView.apply {
                adapter = DishAdapter(recyclerModel.items)
                layoutManager = GridLayoutManager(context, 2)
            }
        }
    }
}