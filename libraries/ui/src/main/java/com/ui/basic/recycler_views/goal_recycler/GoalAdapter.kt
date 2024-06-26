package com.ui.basic.recycler_views.goal_recycler

import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import com.ui.model.GoalModel
import com.ui.common.Constants
import android.view.LayoutInflater
import com.ui.basic.texts.text.Text
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.icon_button.IconButton


class GoalAdapter(
    private var items: List<GoalModel>,
    private val foregroundColor: Int,
    private val backgroundColor: Int,
    private val goalAdapterListener: GoalAdapterListener,
): RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.goal_viewholder, parent, false)

        return GoalViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(items[position], foregroundColor, backgroundColor, goalAdapterListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeAt(position: Int) {
        items = items.subList(0, position) + items.subList(position + 1, items.size)
        notifyItemRemoved(position)
    }

    fun getItemAt(position: Int): GoalModel {
        return items[position]
    }

    interface GoalAdapterListener {
        fun checkUncheckItem(goalModel: GoalModel, checked: Boolean)
        fun onItemRemoved(item: GoalModel, itemsLeft: Int);
    }

    class GoalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemViewContainer = view.findViewById<View>(R.id.item_view_container)
        private val textView = view.findViewById<Text>(R.id.goal_text_view)
        private val buttonView = view.findViewById<IconButton>(R.id.delete_button_icon)
        private val checkboxView = view.findViewById<Checkbox>(R.id.checkbox_view)

        fun bind(item: GoalModel, foregroundColor: Int, backgroundColor: Int, listener: GoalAdapterListener) {
            itemViewContainer.setBackgroundColor(backgroundColor)

            buttonView.setup(
                model = ButtonModel(
                    iconRes = R.drawable.bin_icon,
                    iconSize = Constants.ICON_SIZE_MEDIUM,
                    strokeWidth = Constants.COLOR_ICON_STROKE_WIDTH,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                ),
            )
            textView.setup(
                model = TextModel(
                    textValue = item.textValue,
                    textSize = Constants.TEXT_SIZE_BIG,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor,
                ),
            )
            checkboxView.setup(
                model = CheckBoxModel(
                    checked = item.achieved,
                    active = item.active,
                    backgroundColor = foregroundColor,
                    foregroundColor = backgroundColor,
                    onChecked = {
                        listener.checkUncheckItem(item, true)
                        textView.strikeThrough()
                    },
                    onUnchecked = {
                        listener.checkUncheckItem(item, false)
                        textView.clearThrough()
                    },
                ),
            )
            if (item.achieved || !item.active) { textView.strikeThrough() }
            checkboxView.bringToFront()
        }
    }
}