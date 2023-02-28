package com.ui.basic.recycler_views.metric_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockMetricModel

class MetricAdapter(
    private val items: List<MockMetricModel>,
) : RecyclerView.Adapter<MetricAdapter.MetricViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.metric_viewholder, parent, false)

        return MetricViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MetricViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MetricViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val checkBoxView = view.findViewById<Checkbox>(R.id.checkbox_view)
        private val metricNameView = view.findViewById<Text>(R.id.metric_name_view)
        private val metricValueView = view.findViewById<Text>(R.id.metric_value_view)
        private val metricLabelView = view.findViewById<Text>(R.id.metric_label_view)

        fun bind(item: MockMetricModel) {
            checkBoxView.setup(
                model = CheckBoxModel(
                    checked = false,
                    active = true,
                    onChecked = {},
                    onUnchecked = {},
                )
            )

            metricNameView.setup(
                TextModel(
                    textValue = "textValue",
                    textSize = 20,
                    textColorRes = R.color.white,
                    backgroundColor = R.color.black
                )
            )

            metricValueView.setup(
                model = TextModel(
                    textValue = "textValue 2",
                    textSize = 20,
                    textColorRes = R.color.white,
                    backgroundColor = R.color.black
                )
            )

            metricLabelView.setup(
                model = TextModel(
                    textValue = "text3",
                    textSize = 20,
                    textColorRes = R.color.white,
                    backgroundColor = R.color.black
                )
            )
        }
    }
}