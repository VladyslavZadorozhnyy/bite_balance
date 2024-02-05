package com.ui.basic.nav_bar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat.getColorStateList
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.forEach
import com.ui.common.BaseUiComponent
import com.ui.common.BaseUiComponentModel
import com.ui.components.R
import com.ui.components.databinding.NavigationBarBinding

class NavigationBar(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseUiComponent(context, attrs) {
    private var navigationBarModel: NavigationBarModel? = null
    private var lastSelectedId = -1
    private var second: Boolean = true

    private val binding by lazy {
        NavigationBarBinding.inflate(LayoutInflater.from(context), this).navView
    }

    fun onResume() {
        Log.d("AAADIP", "onResume(), inside navigationBar")
        if (lastSelectedId != -1) {
            binding.selectedItemId = lastSelectedId
            binding.invalidate()
        }
    }

    fun updateForegroundColor(foregroundColor: Int) {
        navigationBarModel = NavigationBarModel(
            nonActiveIconsRes = navigationBarModel?.nonActiveIconsRes ?: listOf(),
            activeIconsRes = navigationBarModel?.activeIconsRes ?: listOf(),
            foregroundColor = foregroundColor,
            backgroundColor = navigationBarModel?.backgroundColor ?: Color.TRANSPARENT,
            onItemSelected = navigationBarModel?.onItemSelected ?: {},
        )
        second = false
        setup(model = navigationBarModel!!)
    }

    fun updateBackgroundColor(backgroundColor: Int) {
        navigationBarModel = NavigationBarModel(
            nonActiveIconsRes = navigationBarModel?.nonActiveIconsRes ?: listOf(),
            activeIconsRes = navigationBarModel?.activeIconsRes ?: listOf(),
            foregroundColor = navigationBarModel?.foregroundColor ?: Color.TRANSPARENT,
            backgroundColor = backgroundColor,
            onItemSelected = navigationBarModel?.onItemSelected ?: {},
        )
        second = false
        setup(model = navigationBarModel!!)
    }

    override fun setup(model: BaseUiComponentModel) {
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
            if (second) binding.selectedItemId = R.id.nav_home
        }
    }

    private fun disableAllItems(model: NavigationBarModel) {
        var index = 0

        binding.menu.forEach {
            val chosenIcon = getDrawable(context, model.nonActiveIconsRes[index])
            DrawableCompat.setTint(chosenIcon!!, Color.DKGRAY) // AAADIP HERE
            it.icon = chosenIcon
            index++
        }
    }

    private fun enableItem(chosen: MenuItem) {
        navigationBarModel?.let {  model ->
            val chosenIconRes = when (chosen.itemId) {
                R.id.nav_home -> model.activeIconsRes[0]
                R.id.nav_stats -> model.activeIconsRes[1]
                R.id.nav_menu -> model.activeIconsRes[2]
                else -> model.activeIconsRes[3]
            }
            val chosenIcon = getDrawable(context, chosenIconRes)
            DrawableCompat.setTint(chosenIcon!!, model.backgroundColor)
            chosen.icon = chosenIcon
        }
    }
}