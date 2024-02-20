package com.ui.components.graph.subcomponents

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.ViewCompat
import com.ui.basic.buttons.common.ButtonModelNew
import com.ui.basic.texts.common.TextModelNew
import com.ui.components.R
import com.ui.components.databinding.SpinnerItemActiveLayoutBinding
import com.ui.components.databinding.SpinnerItemNotActiveLayoutBinding


class SpinnerSubComponent(
    private val spinnerView: Spinner,
) {
    fun setup(
        context: Context,
        spinnerItems: List<Int>,
        foregroundColor: Int,
        backgroundColor: Int,
        onItemSelected: (Int) -> Unit = {}
    ) {
        CustomArrayAdapter(
            context,
            R.layout.spinner_item_active_layout,
            spinnerItems,
            foregroundColor,
            backgroundColor,
        ).apply {
            spinnerView.adapter = this

            spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    chosenIndex: Int,
                    p3: Long,
                ) {
                    onItemSelected.invoke(chosenIndex)
                }
            }
        }
    }

    inner class CustomArrayAdapter(
        context: Context,
        textViewResourceId: Int,
        private val items: List<Int>,
        private val foregroundColor: Int,
        private val backgroundColor: Int,
    ): ArrayAdapter<Int> (context, textViewResourceId, items) {
        private val layoutInflater = LayoutInflater.from(context)

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            SpinnerItemNotActiveLayoutBinding.inflate(layoutInflater).apply {
                textView.setup(
                    TextModelNew(
                        textValue = context.getString(items[position]),
                        textSize = 20,
                        textColor = backgroundColor,
                        backgroundColor = foregroundColor,
                    )
                )

                return root
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            SpinnerItemActiveLayoutBinding.inflate(layoutInflater).apply {
                textView.setOnClickListener(null)

                layoutStroke.backgroundTintList = ColorStateList.valueOf(foregroundColor)
                layoutBody.backgroundTintList = ColorStateList.valueOf(backgroundColor)

                textView.setup(
                    TextModelNew(
                        textValue = context.getString(items[position]),
                        textSize = 25,
                        textColor = foregroundColor,
                        backgroundColor = Color.TRANSPARENT,
                    )
                )

                dropdownButton.setup(
                    ButtonModelNew(
                        iconRes = R.drawable.arrow_down_icon,
                        iconSize = 70,
                        foregroundColor = backgroundColor,
                        backgroundColor = foregroundColor,
                        onClickListener = { spinnerView.performClick() }
                    )
                )
                return root
            }
        }
    }
}