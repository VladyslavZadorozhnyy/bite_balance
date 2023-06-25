package com.ui.basic.recycler_views.meal_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding

class MealRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? MealRecyclerModel)?.let { recyclerModel ->
            val backgroundColor = getColor(context, recyclerModel.backgroundColorRes)

            binding.recyclerView.apply {
                setBackgroundColor(backgroundColor)
                adapter = MealAdapter(recyclerModel.items, recyclerModel.onClickListener)
                layoutManager = LinearLayoutManager(context)
            }

            val touchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    rV: RecyclerView, vH: RecyclerView.ViewHolder, t: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                    (binding.recyclerView.adapter as? MealAdapter)?.let {
                        recyclerModel.onSwipeListener(it.getAt(viewHolder.adapterPosition))
                    }
                }
            }

            val touchHelper = ItemTouchHelper(touchCallback)
            touchHelper.attachToRecyclerView(binding.recyclerView)
        }
    }
}