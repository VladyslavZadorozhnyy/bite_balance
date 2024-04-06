package com.ui.basic.recycler_views.settings_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.components.databinding.RecyclerViewBinding

class SettingsRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {

    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        if (model !is SettingsRecyclerModel) return

        binding.recyclerView.apply {
            adapter = SettingsAdapter(
                items = model.items,
                primaryColor = model.primaryColor,
                secondaryColor = model.secondaryColor,
                onClickListener = model.onClickListener,
            )
            layoutManager = LinearLayoutManager(context)
            setBackgroundColor(model.primaryColor)
        }
    }
}