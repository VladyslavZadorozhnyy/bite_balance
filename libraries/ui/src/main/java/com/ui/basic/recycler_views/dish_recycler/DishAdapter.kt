package com.ui.basic.recycler_views.dish_recycler

import android.view.View
import com.ui.components.R
import com.ui.model.DishModel
import android.view.ViewGroup
import com.ui.common.Constants
import android.view.LayoutInflater
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.slideable_text.SlideText
import com.ui.basic.click_listeners.BaseClickListener

class DishAdapter(
    private val items: List<DishModel>,
    private val primaryColor: Int,
    private val secondaryColor: Int,
    private val onClickListener: (DishModel) -> Unit,
): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.dish_viewholder, parent, false)

        inflatedView.backgroundTintList = ColorStateList.valueOf(primaryColor)
        return DishViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(items[position], primaryColor, secondaryColor)
        holder.itemView.setOnClickListener {
            BaseClickListener.processClick { onClickListener(items[position]) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class DishViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<SlideText>(R.id.dish_name_view)
        private val dishIconView = view.findViewById<IconButton>(R.id.dish_icon_view)

        fun bind(item: DishModel, primaryColor: Int, secondaryColor: Int) {
            textView.setup(
                model = TextModel(
                    textValue = item.name,
                    textSize = Constants.TEXT_SIZE,
                    textColor = secondaryColor,
                    backgroundColor = primaryColor,
                ),
            )
            dishIconView.setup(
                model = ButtonModel(
                    iconRes = item.iconRes,
                    iconSize = Constants.DISH_ICON_SIZE,
                    foregroundColor = secondaryColor,
                    backgroundColor = primaryColor,
                    onClickListener = { itemView.callOnClick() },
                ),
            )
            dishIconView.rootView.findViewById<View>(R.id.button_view).stateListAnimator = null
        }
    }
}