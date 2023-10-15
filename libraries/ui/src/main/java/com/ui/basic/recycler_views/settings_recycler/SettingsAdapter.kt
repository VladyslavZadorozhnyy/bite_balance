package com.ui.basic.recycler_views.settings_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.buttons.icon_button.IconButton
import com.ui.basic.texts.common.TextModelNew
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockInstructionModel

class SettingsAdapter(
    private val items: List<MockInstructionModel>,
    private val primaryColor: Int,
    private val secondaryColor: Int,
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
        holder.bind(items[position], primaryColor, secondaryColor)
        holder.itemView.setOnClickListener { onClickListener(items[position]) }
    }

    class SettingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val settingIconButton = view.findViewById<IconButton>(R.id.setting_icon)
        private val settingText = view.findViewById<Text>(R.id.setting_text)
        private val settingBar = view.findViewById<View>(R.id.setting_bar)

        fun bind(model: MockInstructionModel, primaryColor: Int, secondaryColor: Int) {
            itemView.setBackgroundColor(secondaryColor)
            settingBar.setBackgroundColor(primaryColor)

            settingIconButton.setup(
                model = ButtonModelNew(
                    iconRes = model.iconRes,
                    iconSize = 120,
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                    onClickListener = { itemView.callOnClick() }
                )
            )
            settingIconButton.rootView.findViewById<View>(R.id.button_view).stateListAnimator = null

            settingText.setup(
                model = TextModelNew(
                    textValue = model.instructionText,
                    textSize = 20,
                    textColor = primaryColor,
                    backgroundColor = secondaryColor
                )
            )
        }
    }
}