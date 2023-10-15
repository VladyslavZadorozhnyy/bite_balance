package com.ui.basic.recycler_views.settings_recycler

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.databinding.RecyclerViewBinding

class SettingsRecycler(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {

    private val binding by lazy {
        RecyclerViewBinding.inflate(LayoutInflater.from(context), this)
    }

    override fun setup(model: BaseUiComponentModel) {
        (model as? SettingsRecyclerModel)?.let { recyclerModel ->
            binding.recyclerView.apply {
                adapter = SettingsAdapter(
                    recyclerModel.items,
                    recyclerModel.primaryColor,
                    recyclerModel.secondaryColor,
                    model.onClickListener,
                )
                layoutManager = LinearLayoutManager(context)
                setBackgroundColor(recyclerModel.primaryColor)
            }
        }
    }
}