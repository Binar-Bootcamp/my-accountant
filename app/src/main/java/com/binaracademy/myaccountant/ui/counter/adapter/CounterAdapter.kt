package com.binaracademy.myaccountant.ui.counter.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.databinding.CounterChildBinding
import com.binaracademy.myaccountant.ui.counter.FilterItem

class CounterAdapter : RecyclerView.Adapter<CounterViewHolder>() {
    var items: MutableList<CounterData> = mutableListOf()
    private lateinit var filterList :ArrayList<CounterData>
    private var filter: FilterItem? = null
    private lateinit var context: Context




    @SuppressLint("NotifyDataSetChanged")
    fun addItems(newItems: List<CounterData>) {
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    //    var items : List<String> = emptyList()
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