package com.ui.basic.recycler_views.meal_recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockMealModel

class MealAdapter (
    private var items: List<MockMealModel>,
): RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.meal_viewholder, parent, false)

        return MealViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAt(position: Int) {
        items = items.subList(0, position) + items.subList(position + 1, items.size)
        notifyDataSetChanged()
    }

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mealIcon = view.findViewById<IconButton>(R.id.meal_icon)
        private val clockIcon = view.findViewById<IconButton>(R.id.clock_icon)
        private val textView = view.findViewById<Text>(R.id.goal_text_view)
        private val buttonView = view.findViewById<IconButton>(R.id.delete_button_icon)

        fun bind(item: MockMealModel) {
            mealIcon.setup(
                model = ButtonModel(
                    iconRes = R.drawable.breakfast_icon,
                    iconSize = 120,
                    strokeWidth = 0,
                    foregroundColorRes = R.color.black,
                    backgroundColorRes = R.color.white
                )
            )
            mealIcon.bringToFront()

            clockIcon.setup(
                model = ButtonModel(
                    iconRes = R.drawable.breakfast_icon,
                    iconSize = 120,
                    strokeWidth = 0,
                    foregroundColorRes = R.color.black,
                    backgroundColorRes = R.color.white
                )
            )
            clockIcon.bringToFront()

            buttonView.setup(
                model = ButtonModel(
                    iconRes = R.drawable.bin_icon,
                    iconSize = 80,
                    strokeWidth = 5,
                )
            )
            textView.setup(
                model = TextModel(
                    textValue = "Meal view",
                    textSize = 30,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.white
                )
            )
        }
    }
}