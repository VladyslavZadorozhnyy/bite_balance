package com.ui.basic.recycler_views.meal_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.components.databinding.RecyclerViewBinding

class MealRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is MealRecyclerModel) return

        binding.recyclerView.apply {
            setBackgroundColor(model.backgroundColor)
            adapter = MealAdapter(
                items = model.items,
                foregroundColor = model.foregroundColor,
                backgroundColor = model.backgroundColor,
                onClickListener = model.onClickListener,
            )
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
                    model.onSwipeListener(it.getAt(viewHolder.adapterPosition))
                }
            }
        }
        val touchHelper = ItemTouchHelper(touchCallback)
        touchHelper.attachToRecyclerView(binding.recyclerView)
    }
}