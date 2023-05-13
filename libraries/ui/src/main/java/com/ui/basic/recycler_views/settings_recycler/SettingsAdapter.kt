package com.ui.basic.recycler_views.settings_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModel
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockInstructionModel
import com.ui.mocks.MockMealModel

class SettingsAdapter(
    private val items: List<MockInstructionModel>,
    private val onClickListener: (MockInstructionModel) -> Unit
): RecyclerView.Adapter<SettingsAdapter.SettingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.setting_viewholder, parent, false)

        return SettingViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onClickListener(items[position]) }
    }

    class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val settingIconButton = view.findViewById<IconButton>(R.id.setting_icon)
        private val settingText = view.findViewById<Text>(R.id.setting_text)

        fun bind(model: MockInstructionModel) {
            settingIconButton.setup(
                model = ButtonModel(
                    iconRes = model.iconRes,
                    iconSize = 120,
                    foregroundColorRes = R.color.black,
                    backgroundColorRes = R.color.white,
                    onClickListener = { itemView.callOnClick() }
                )
            )

            settingText.setup(
                model = TextModel(
                    textValue = model.instructionText,
                    textSize = 20,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.white
                )
            )
        }
    }
}