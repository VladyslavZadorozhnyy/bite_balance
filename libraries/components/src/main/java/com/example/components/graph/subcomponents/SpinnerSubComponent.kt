package com.example.components.graph.subcomponents

import com.example.components.R
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.components.buttons.common.ButtonModel
import com.example.components.common.ComponentUtils
import com.example.components.databinding.SpinnerItemActiveLayoutBinding
import com.example.components.databinding.SpinnerItemNotActiveLayoutBinding
import com.example.components.texts.common.TextModel


class SpinnerSubComponent(
    private val spinnerView: Spinner
) {
    fun setup(
        context: Context,
        spinnerItems: List<String>,
        onItemSelected: (Int) -> Unit = {}
    ) {
        CustomArrayAdapter(context, R.layout.spinner_item_active_layout, spinnerItems).apply {
            spinnerView.adapter = this

            spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {}

                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    chosenIndex: Int,
                    p3: Long)
                {
                    onItemSelected.invoke(chosenIndex)
                }
            }
        }
    }

    inner class CustomArrayAdapter(
        context: Context,
        textViewResourceId: Int,
        private val items: List<String>,
    ): ArrayAdapter<String> (context, textViewResourceId, items) {
        private val layoutInflater = LayoutInflater.from(context)

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            SpinnerItemNotActiveLayoutBinding.inflate(layoutInflater).apply {
                textView.setup(
                    TextModel(
                        textValue = items[position],
                        textSize = 20,
                        textColor = Color.WHITE,
                        backgroundColor = Color.BLACK
                    )
                )

                return root
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            SpinnerItemActiveLayoutBinding.inflate(layoutInflater).apply {
                textView.setOnClickListener(null)

                textView.setup(
                    TextModel(
                        textValue = items[position],
                        textSize = 25,
                        textColor = Color.BLACK,
                        backgroundColor = Color.TRANSPARENT
                    )
                )

                dropdownButton.setup(
                    ButtonModel(
                        iconRes = R.drawable.arrow_down_icon,
                        iconSize = ComponentUtils.dpToPx(context, 20),
                        foregroundColorRes = R.color.white,
                        backgroundColorRes = R.color.black,
                        onClickListener = { spinnerView.performClick() }
                    )
                )
                return root
            }
        }
    }
}