package com.ui.basic.recycler_views.settings_recycler

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

class SettingsAdapter(
    private val items: List<InstructionModel>,
    private val primaryColor: Int,
    private val secondaryColor: Int,
    private val onClickListener: (InstructionModel) -> Unit,
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

        fun bind(model: InstructionModel, primaryColor: Int, secondaryColor: Int) {
            itemView.setBackgroundColor(secondaryColor)
            settingBar.setBackgroundColor(primaryColor)

            settingIconButton.setup(
                model = ButtonModel(
                    iconRes = model.iconRes,
                    iconSize = Constants.ICON_SIZE_LARGE,
                    foregroundColor = primaryColor,
                    backgroundColor = secondaryColor,
                    onClickListener = { itemView.callOnClick() },
                ),
            )
            settingIconButton.rootView.findViewById<View>(R.id.button_view).stateListAnimator = null

            settingText.setup(
                model = TextModel(
                    textValue = model.instructionText,
                    textSize = Constants.TEXT_SIZE,
                    textColor = primaryColor,
                    backgroundColor = secondaryColor,
                ),
            )
        }
    }
}