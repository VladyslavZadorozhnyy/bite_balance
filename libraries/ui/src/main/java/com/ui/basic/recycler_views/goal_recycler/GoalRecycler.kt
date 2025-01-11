package com.ui.basic.recycler_views.goal_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.components.databinding.RecyclerViewBinding

class GoalRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is GoalRecyclerModel) return

        binding.recyclerView.apply {
            setBackgroundColor(model.foregroundColor)
            adapter = GoalAdapter(
                items = model.items,
                foregroundColor = model.foregroundColor,
                backgroundColor = model.backgroundColor,
                goalAdapterListener = model.goalAdapterListener,
            )
            layoutManager = LinearLayoutManager(context)
        }
        val touchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                (binding.recyclerView.adapter as? GoalAdapter)?.let {
                    model.goalAdapterListener.onItemRemoved(
                        item = it.getItemAt(viewHolder.adapterPosition),
                        itemsLeft = it.itemCount - 1,
                    )
                    it.removeAt(viewHolder.adapterPosition)
                }
            }
        }
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(binding.recyclerView)
    }
}