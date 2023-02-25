package com.ui.basic.recycler_views.goal_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.GoalRecyclerBinding

class GoalRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        GoalRecyclerBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? GoalRecyclerModel)?.let { recyclerModel ->
            val backgroundColor = getColor(context, recyclerModel.backgroundColorRes)

            binding.recyclerView.apply {
                setBackgroundColor(backgroundColor)
                adapter = GoalAdapter(recyclerModel.items)
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
                    (binding.recyclerView.adapter as? GoalAdapter)?.removeAt(viewHolder.adapterPosition)
                }
            }

            val touchHelper = ItemTouchHelper(touchCallback)
            touchHelper.attachToRecyclerView(binding.recyclerView)
        }
    }


}