package com.ui.basic.recycler_views.meal_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.common.TextModelNew
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.model.MealModelUnboxed

class MealAdapter (
    private var items: List<MealModelUnboxed>,
    private val foregroundColor: Int,
    private val backgroundColor: Int,
    private val onClickListener: (MealModelUnboxed) -> Unit
): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.meal_viewholder, parent, false)

        return MealViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(items[position], foregroundColor, backgroundColor)
        holder.itemView.setOnClickListener { onClickListener(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAt(position: Int) {
        items = items.subList(0, position) + items.subList(position + 1, items.size)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,  items.size - position)
    }

    fun getAt(position: Int) = items[position]

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val timeView = view.findViewById<Text>(R.id.time_text_view)
        private val textView = view.findViewById<Text>(R.id.meal_text_view)
        private val mealIcon = view.findViewById<IconButton>(R.id.meal_icon)
        private val clockIcon = view.findViewById<IconButton>(R.id.clock_icon)
        private val buttonView = view.findViewById<IconButton>(R.id.delete_button_icon)

        fun bind(item: MealModelUnboxed, foregroundColor: Int, backgroundColor: Int) {
            (mealIcon.parent as? View)?.setBackgroundColor(foregroundColor)
            mealIcon.setup(
                model = ButtonModelNew(
                    iconRes = R.drawable.breakfast_icon,
                    iconSize = 120,
                    strokeWidth = 0,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                    onClickListener = { itemView.callOnClick() }
                )
            )

            clockIcon.setup(
                model = ButtonModelNew(
                    iconRes = R.drawable.clock_icon,
                    iconSize = 90,
                    strokeWidth = 0,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                    onClickListener = { itemView.callOnClick() }
                )
            )

            buttonView.setup(
                model = ButtonModelNew(
                    iconRes = R.drawable.bin_icon,
                    iconSize = 80,
                    strokeWidth = 5,
                    backgroundColor = backgroundColor,
                    foregroundColor = foregroundColor,
                )
            )

            textView.setup(
                model = TextModelNew(
                    textValue = item.dishName,
                    textSize = 20,
                    textColor = backgroundColor,
                    backgroundColor = foregroundColor
                )
            )

            timeView.setup(
                model = TextModelNew(
                    textValue = item.mealTime,
                    textSize = 17,
                    textColor = backgroundColor,
                    backgroundColor = foregroundColor
                )
            )
        }
    }
}