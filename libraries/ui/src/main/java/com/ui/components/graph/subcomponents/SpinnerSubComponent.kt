package com.ui.components.graph.subcomponents

import android.view.View
import com.ui.components.R
import android.widget.Spinner
import android.graphics.Color
import android.view.ViewGroup
import android.content.Context
import com.ui.common.Constants
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.view.LayoutInflater
import android.content.res.ColorStateList
import com.ui.basic.texts.common.TextModel
import com.ui.basic.buttons.common.ButtonModel
import android.graphics.drawable.ColorDrawable
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
        onItemSelected: (Int) -> Unit = {},
    ) {
        CustomArrayAdapter(
            context = context,
            textViewResourceId = R.layout.spinner_item_active_layout,
            items = spinnerItems,
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
        ).apply {
            spinnerView.adapter = this
            spinnerView.setPopupBackgroundDrawable(ColorDrawable(foregroundColor))
            spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    chosenIndex: Int,
                    p3: Long,
                ) { onItemSelected.invoke(chosenIndex) }
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
        private val binding = SpinnerItemActiveLayoutBinding.inflate(layoutInflater)

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            SpinnerItemNotActiveLayoutBinding.inflate(layoutInflater).apply {
                textView.setup(
                    model = TextModel(
                        textValue = context.getString(items[position]),
                        textSize = Constants.TEXT_SIZE,
                        textColor = backgroundColor,
                        backgroundColor = Color.TRANSPARENT,
                    ),
                )
                return root
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            binding.apply {
                textView.setOnClickListener(null)

                layoutStroke.backgroundTintList = ColorStateList.valueOf(foregroundColor)
                layoutBody.backgroundTintList = ColorStateList.valueOf(backgroundColor)
                textView.setup(
                    model = TextModel(
                        textValue = context.getString(items[position]),
                        textSize = Constants.TEXT_SIZE_MD,
                        textColor = foregroundColor,
                        backgroundColor = Color.TRANSPARENT,
                    ),
                )
                dropdownButton.setup(
                    model = ButtonModel(
                        iconRes = R.drawable.arrow_down_icon,
                        iconSize = Constants.ICON_SIZE_BIG,
                        foregroundColor = backgroundColor,
                        backgroundColor = foregroundColor,
                        onClickListener = { spinnerView.performClick() },
                    ),
                )
                return root
            }
        }
    }
}