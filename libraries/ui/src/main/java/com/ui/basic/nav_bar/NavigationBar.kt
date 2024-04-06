package com.ui.basic.nav_bar

import com.ui.components.R
import android.view.MenuItem
import android.graphics.Color
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ui.common.BaseUiComponent
import android.content.res.ColorStateList
import androidx.core.view.forEachIndexed
import com.ui.common.BaseUiComponentModel
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.content.ContextCompat.getDrawable
import com.ui.components.databinding.NavigationBarBinding

class NavigationBar(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private var navigationBarModel: NavigationBarModel? = null
    private var lastSelectedId = -1

    private val binding by lazy {
        NavigationBarBinding.inflate(LayoutInflater.from(context), this).navView
    }

    override fun onResume() {
        super.onResume()
        if (lastSelectedId == -1) return

        binding.selectedItemId = lastSelectedId
        binding.invalidate()
    }

    fun updateForegroundColor(foregroundColor: Int) {
        navigationBarModel = NavigationBarModel(
            navIcons = navigationBarModel?.navIcons ?: listOf(),
            foregroundColor = foregroundColor,
            backgroundColor = navigationBarModel?.backgroundColor ?: Color.TRANSPARENT,
            onItemSelected = navigationBarModel?.onItemSelected ?: {},
        )
        setup(model = navigationBarModel!!)
    }

    fun updateBackgroundColor(backgroundColor: Int) {
        navigationBarModel = NavigationBarModel(
            navIcons = navigationBarModel?.navIcons ?: listOf(),
            foregroundColor = navigationBarModel?.foregroundColor ?: Color.TRANSPARENT,
            backgroundColor = backgroundColor,
            onItemSelected = navigationBarModel?.onItemSelected ?: {},
        )
        setup(model = navigationBarModel!!)
    }

    override fun setup(model: BaseUiComponentModel) {
        val isInitialSetup = navigationBarModel == null && (model as? NavigationBarModel) != null
        navigationBarModel = model as? NavigationBarModel
        navigationBarModel?.let {
            binding.itemIconTintList = null
            binding.backgroundTintList = ColorStateList.valueOf(it.foregroundColor)

            binding.setOnItemSelectedListener { chosen ->
                disableAllItems(it)
                enableItem(chosen)
                if (lastSelectedId == -1 || lastSelectedId != chosen.itemId) {
                    lastSelectedId = chosen.itemId
                    it.onItemSelected.invoke(chosen.itemId)
                }
                return@setOnItemSelectedListener true
            }
            if (isInitialSetup) binding.selectedItemId = R.id.nav_home
        }
    }

    private fun disableAllItems(model: NavigationBarModel) {
        binding.menu.forEachIndexed { index, item ->
            val chosenIcon = getDrawable(context, model.navIcons[index])
            DrawableCompat.setTint(chosenIcon!!, Color.DKGRAY)
            item.icon = chosenIcon
        }
    }

    private fun enableItem(chosen: MenuItem) {
        if (navigationBarModel == null) return

        val chosenIcon = getDrawable(context, navigationBarModel!!.chosenIdToIndex(chosen.itemId))
        DrawableCompat.setTint(chosenIcon!!, navigationBarModel!!.backgroundColor)
        chosen.icon = chosenIcon
    }
}