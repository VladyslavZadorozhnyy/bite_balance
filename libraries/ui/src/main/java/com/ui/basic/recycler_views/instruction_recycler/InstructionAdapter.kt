package com.ui.basic.recycler_views.instruction_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.common.TextModelNew
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockInstructionModel

class InstructionAdapter(
    private var items: List<MockInstructionModel>,
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

        fun bind(item: MockInstructionModel, foregroundColor: Int, backgroundColor: Int) {
            buttonIcon.setup(
                model = ButtonModelNew(
                    iconRes = item.iconRes,
                    iconSize = 100,
                    strokeWidth = 0,
                    foregroundColor = backgroundColor,
                    backgroundColor = foregroundColor,
                )
            )
            buttonInstruction.setup(
                model = TextModelNew(
                    textValue = item.instructionText,
                    textSize = 20,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor,
                )
            )
        }
    }
}