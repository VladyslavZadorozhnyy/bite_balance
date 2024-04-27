package com.ui.basic.recycler_views.instruction_recycler

import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import com.ui.common.Constants
import android.view.LayoutInflater
import com.ui.basic.texts.text.Text
import com.ui.model.InstructionModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.icon_button.IconButton

class InstructionAdapter(
    private var items: List<InstructionModel>,
    private var backgroundColor: Int,
    private var foregroundColor: Int,
): RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.instruction_viewholder, parent, false)

        return InstructionViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        holder.bind(items[position], foregroundColor, backgroundColor)
    }

    class InstructionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val buttonIcon = view.findViewById<IconButton>(R.id.button_icon)
        private val buttonInstruction = view.findViewById<Text>(R.id.button_instruction)

        fun bind(item: InstructionModel, foregroundColor: Int, backgroundColor: Int) {
            buttonIcon.setup(
                model = ButtonModel(
                    iconRes = item.iconRes,
                    iconSize = Constants.ICON_SIZE_BIG,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                    isClickable = false,
                ),
            )
            buttonInstruction.setup(
                model = TextModel(
                    textValue = item.instructionText,
                    textSize = Constants.CORNER_RADIUS,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor,
                ),
            )
        }
    }
}