package com.example.components.nav_bar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.view.forEach
import com.example.components.R
import com.example.components.common.BaseComponent
import com.example.components.common.BaseComponentModel
import com.example.components.databinding.NavigationBarBinding

class NavigationBar(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseComponent(context, attrs) {
    private val binding by lazy {
        NavigationBarBinding.inflate(LayoutInflater.from(context), this).navView
    }

    override fun setup(model: BaseComponentModel) {
        (model as? NavigationBarModel)?.let { model ->
            binding.itemIconTintList = null
            binding.backgroundTintList = getColorStateList(context, R.color.black)

            binding.setOnItemSelectedListener { chosen ->
                disableAllItems(model)
                enableItem(model, chosen)

                model.onItemSelected.invoke(chosen.itemId)
                return@setOnItemSelectedListener true
            }

            binding.setSelectedItemId(R.id.nav_home)
        }
    }

    private fun disableAllItems(model: NavigationBarModel) {
        var index = 0

        binding.menu.forEach {
            it.setIcon(model.nonActiveIconsRes[index])
            index++
        }
    }

    private fun enableItem(model: NavigationBarModel, chosen: MenuItem) {
        when (chosen.itemId) {
            R.id.nav_home -> chosen.setIcon(model.activeIconsRes[0])
            R.id.nav_stats -> chosen.setIcon(model.activeIconsRes[1])
            R.id.nav_menu -> chosen.setIcon(model.activeIconsRes[2])
            else -> chosen.setIcon(model.activeIconsRes[3])
        }
    }

}