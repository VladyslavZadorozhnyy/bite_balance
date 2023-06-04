package com.ui.basic.recycler_views.metric_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.input_form.InputForm
import com.ui.basic.input_form.InputFormModel
import com.ui.basic.texts.common.TextModel
import com.ui.basic.texts.text.Text
import com.ui.components.R
import com.ui.mocks.MockMetricModel

class MetricAdapter(
    private val items: List<MockMetricModel>,
) : RecyclerView.Adapter<MetricAdapter.MetricViewHolder>() {

    private val inputValues: MutableList<String> = MutableList(items.size) { "" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.metric_viewholder, parent, false)

        return MetricViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: MetricViewHolder, position: Int) {
        holder.position = position
        holder.bind(items[position], inputValues)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getInputValues(): List<String> {
        return inputValues
    }

    class MetricViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var position = -1

        private val checkBoxView = view.findViewById<Checkbox>(R.id.checkbox_view)
        private val metricNameView = view.findViewById<Text>(R.id.metric_name_view)
        private val metricValueViewInput = view.findViewById<InputForm>(R.id.metric_value_view_input)
        private val metricValueViewText = view.findViewById<Text>(R.id.metric_value_view_text)
        private val metricSuffixView = view.findViewById<Text>(R.id.metric_label_view)

        fun setPosition(value: Int) {
            position = value
        }

        fun bind(model: MockMetricModel, inputValues: MutableList<String>) {
            setupMetricName(model.name)
            if (model.editable) {
                setupCheckBox(inputValues)
            } else {
                checkBoxView.visibility = View.INVISIBLE
            }

            if (model.suffix.isNotEmpty()) {
                setupMetricSuffix(model.suffix)
            } else {
                metricSuffixView.visibility = View.GONE
            }

            if (model.editable) {
                setupMetricValueInput(true, inputValues)
                metricValueViewInput.visibility = View.VISIBLE
            } else {
                setupMetricValueText(value = model.hint)
                metricValueViewText.visibility = View.VISIBLE
            }

            if (model.onlyNumbers) { setupOnlyNumbers() }
        }

        private fun setupCheckBox(inputValues: MutableList<String>) {
            checkBoxView.setup(
                model = CheckBoxModel(
                    checked = false,
                    active = true,
                    onChecked = {
                        setupMetricValueInput(false, inputValues)
                        metricNameView.strikeThrough()
                        metricSuffixView.strikeThrough()
                    },
                    onUnchecked = {
                        setupMetricValueInput(active = true, inputValues)
                        metricNameView.unstrikeThrough()
                        metricSuffixView.unstrikeThrough()
                    },
                )
            )
        }

        private fun setupMetricValueInput(active: Boolean, inputValues: MutableList<String>) {
            metricValueViewInput.setup(
                model = InputFormModel(
                    active = active,
                    hint = inputValues[position],
                    onInputChange = { inputValues[position] = it }
                )
            )
        }

        fun setupOnlyNumbers() {
            metricValueViewInput.setupOnlyNumbers()
        }

        private fun setupMetricValueText(value: String) {
            metricValueViewText.setup(
                model = TextModel(
                    textValue = value,
                    textSize = 18,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.transparent
                )
            )
        }

        private fun setupMetricName(value: String) {
            metricNameView.setup(
                TextModel(
                    textValue = value,
                    textSize = 18,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.transparent
                )
            )
        }

        private fun setupMetricSuffix(value: String) {
            metricSuffixView.setup(
                model = TextModel(
                    textValue = value,
                    textSize = 18,
                    textColorRes = R.color.black,
                    backgroundColor = R.color.transparent
                )
            )
            metricSuffixView.visibility = View.VISIBLE
        }
    }
}