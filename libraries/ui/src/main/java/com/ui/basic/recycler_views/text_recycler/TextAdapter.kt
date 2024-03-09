package com.ui.basic.recycler_views.text_recycler

import android.view.View
import com.ui.components.R
import android.view.ViewGroup
import android.view.LayoutInflater
import com.ui.basic.texts.text.Text
import com.ui.basic.texts.common.TextModel
import androidx.recyclerview.widget.RecyclerView


class TextAdapter(
    private val items: List<TextModel>,
    private val onClickListener: (TextModel) -> Unit,
): RecyclerView.Adapter<TextAdapter.TextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView = layoutInflater.inflate(R.layout.text_viewholder, parent, false)

        return TextViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener { onClickListener(items[position]) }
    }

    override fun getItemCount(): Int { return items.size }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<Text>(R.id.text_view)

        fun bind(model: TextModel) { textView.setup(model) }
    }
}