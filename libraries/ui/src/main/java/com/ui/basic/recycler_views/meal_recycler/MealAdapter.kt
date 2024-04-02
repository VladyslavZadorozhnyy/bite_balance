package com.ui.basic.recycler_views.meal_recycler

import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import com.ui.common.Constants
import android.view.LayoutInflater
import com.ui.basic.texts.text.Text
import com.ui.model.MealModelUnboxed
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.icon_button.IconButton

class MealAdapter (
    private var items: List<MealModelUnboxed>,
    private val foregroundColor: Int,
    private val backgroundColor: Int,
    private val onClickListener: (MealModelUnboxed) -> Unit,
): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.meal_viewholder, parent, false)

        return MealViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(items[position], foregroundColor, backgroundColor)
        holder.itemView.setOnClickListener {
            if (items[position].dishId != -1L) onClickListener(items[position]) }
    }

    override fun getItemCount(): Int {
        return items.size
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
                model = ButtonModel(
                    iconRes = item.iconRes,
                    iconSize = Constants.ICON_SIZE_LARGE,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                    onClickListener = { callClick(item, itemView) }
                )
            )
            clockIcon.setup(
                model = ButtonModel(
                    iconRes = R.drawable.clock_icon,
                    iconSize = Constants.MEAL_ICON_SIZE,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                    onClickListener = { callClick(item, itemView) }
                )
            )
            buttonView.setup(
                model = ButtonModel(
                    iconRes = R.drawable.bin_icon,
                    iconSize = Constants.ICON_SIZE_MEDIUM,
                    strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                    backgroundColor = backgroundColor,
                    foregroundColor = foregroundColor,
                ),
            )
            textView.setup(
                model = TextModel(
                    textValue = item.dishName,
                    textSize = Constants.TEXT_SIZE,
                    textColor = backgroundColor,
                    backgroundColor = foregroundColor
                )
            )
            timeView.setup(
                model = TextModel(
                    textValue = item.mealTime,
                    textSize = Constants.TEXT_SIZE_SMALL,
                    textColor = backgroundColor,
                    backgroundColor = foregroundColor
                )
            )
        }

        private fun callClick(item: MealModelUnboxed, itemView: View) {
            if (item.dishId != -1L) itemView.callOnClick()
        }
    }
}