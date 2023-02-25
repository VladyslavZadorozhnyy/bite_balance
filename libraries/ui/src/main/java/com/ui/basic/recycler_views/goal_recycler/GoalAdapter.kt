package com.ui.basic.recycler_views.goal_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R


class GoalAdapter(
    private var items: List<String>,
): RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.goal_viewholder, parent, false)

        return GoalViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAt(position: Int) {
        items = items.subList(0, position) + items.subList(position + 1, items.size)
        notifyDataSetChanged()
    }

    class GoalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<Text>(R.id.goal_text_view)
        private val buttonView = view.findViewById<IconButton>(R.id.button_icon)
        private val checkboxView = view.findViewById<Checkbox>(R.id.checkbox_view)

        fun bind(item: String) {
            buttonView.setup(
                model = ButtonModel(
                    iconRes = R.drawable.bin_icon,
                    iconSize = 80,
                    strokeWidth = 5,
                )
            )

            textView.setup(
                model = TextModel(
                    textValue = item,
                    textSize = 30,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.white
                )
            )

            checkboxView.setup(
                model = CheckBoxModel(
                    checked = false,
                    active = true,
                    onChecked = { textView.strikeThrough() },
                    onUnchecked = { textView.unstrikeThrough() },
                )
            )
            checkboxView.bringToFront()
        }
    }
}