package com.ui.basic.recycler_views.metric_recycler

import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import com.ui.common.Constants
import com.ui.model.MetricModel
import android.view.LayoutInflater
import com.ui.basic.checkbox.Checkbox
import com.ui.basic.input_form.InputForm
import com.ui.basic.texts.common.TextModel
import com.ui.basic.checkbox.CheckBoxModel
import com.ui.basic.input_form.InputFormModel
import androidx.recyclerview.widget.RecyclerView
import com.ui.basic.texts.slideable_text.SlideText


class MetricAdapter(
    private val items: List<MetricModel>,
    private val foregroundColor: Int,
    private val backgroundColor: Int,
) : RecyclerView.Adapter<MetricAdapter.MetricViewHolder>() {

    private val inputValues: MutableList<String> = MutableList(items.size) { "" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.metric_viewholder, parent, false)

        return MetricViewHolder(inflatedView, backgroundColor, foregroundColor)
    }

    override fun onBindViewHolder(holder: MetricViewHolder, position: Int) {
        holder.position = position
        holder.bind(items[position], inputValues, items)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getInputValues(): List<String> {
        return inputValues
    }

    class MetricViewHolder(
        view: View,
        val foregroundColor: Int,
        val backgroundColor: Int,
    ) : RecyclerView.ViewHolder(view) {
        private var position = -1

        private val checkBoxView = view.findViewById<Checkbox>(R.id.checkbox_view)
        private val metricNameView = view.findViewById<SlideText>(R.id.metric_name_view)
        private val metricValueViewInput = view.findViewById<InputForm>(R.id.metric_value_view_input)
        private val metricValueViewText = view.findViewById<SlideText>(R.id.metric_value_view_text)
        private val metricSuffixView = view.findViewById<SlideText>(R.id.metric_label_view)

        fun setPosition(value: Int) { position = value }

        fun bind(
            model: MetricModel,
            inputValues: MutableList<String>,
            items: List<MetricModel>,
        ) {
            itemView.setBackgroundColor(backgroundColor)
            setupMetricName(model.name)

            if (model.editable) setupCheckBox(inputValues)
            else checkBoxView.visibility = View.INVISIBLE

            if (model.suffix.isNotEmpty()) setupMetricSuffix(model.suffix)
            else metricSuffixView.visibility = View.INVISIBLE

            if (model.editable) {
                setupMetricValueInput(true, inputValues, items)
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
                        metricNameView.clearThrough()
                        metricSuffixView.clearThrough()
                    },
                    backgroundColor = backgroundColor,
                    foregroundColor = foregroundColor,
                ),
            )
        }

        private fun setupMetricValueInput(
            active: Boolean,
            inputValues: MutableList<String>,
            items: List<MetricModel>  = emptyList(),
        ) {
            metricValueViewInput.setup(
                model = InputFormModel(
                    active = active,
                    hint = items.getOrNull(position)?.hint ?: inputValues[position],
                    onInputChange = { inputValues[position] = it },
                    foregroundColor = foregroundColor,
                    backgroundColor = backgroundColor,
                ),
            )
        }

        private fun setupOnlyNumbers() {
            metricValueViewInput.setupOnlyNumbers()
        }

        private fun setupMetricValueText(value: String) {
            metricValueViewText.setup(
                model = TextModel(
                    textValue = value,
                    textSize = Constants.TEXT_SIZE,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor
                ),
            )
        }

        private fun setupMetricName(value: String) {
            metricNameView.setup(
                model = TextModel(
                    textValue = value,
                    textSize = Constants.TEXT_SIZE,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor,
                ),
            )
        }

        private fun setupMetricSuffix(value: String) {
            metricSuffixView.setup(
                model = TextModel(
                    textValue = value,
                    textSize = Constants.TEXT_SIZE,
                    textColor = foregroundColor,
                    backgroundColor = backgroundColor,
                ),
            )
            metricSuffixView.visibility = View.VISIBLE
        }
    }
}