package com.binaracademy.myaccountant.ui.counter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.model.CounterData
import com.binaracademy.myaccountant.databinding.CounterChildBinding

class CounterAdapter : RecyclerView.Adapter<CounterViewHolder>() {
    private val items: MutableList<CounterData> = mutableListOf()

    fun addItems(newItems: List<CounterData>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.counter_child, parent, false)
        val binding = CounterChildBinding.bind(view)
        return CounterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
}