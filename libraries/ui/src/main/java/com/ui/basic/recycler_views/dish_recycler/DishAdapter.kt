package com.ui.basic.recycler_views.dish_recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockDishModel


class DishAdapter(
    private val items: List<MockDishModel>
): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.dish_viewholder, parent, false)

        inflatedView.backgroundTintList = getColorStateList(parent.context, R.color.black)
        return DishViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class DishViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<Text>(R.id.dish_name_view)
        private val dishIconView = view.findViewById<IconButton>(R.id.dish_icon_view)

        init {
            view.setOnClickListener {
                Log.d("AAADIP", "setOnClickListener called")
            }
        }

        fun bind(item: MockDishModel) {
            textView.setup(
                model = TextModel(
                    textValue = item.name,
                    textSize = 20,
                    textColorRes = R.color.white,
                    backgroundColor = R.color.black
                )
            )

            dishIconView.setup(
                model = ButtonModel(
                    iconRes = item.iconRes,
                    iconSize = 300,
                    foregroundColorRes = R.color.white,
                    backgroundColorRes = R.color.black
                )
            )
        }
    }
}