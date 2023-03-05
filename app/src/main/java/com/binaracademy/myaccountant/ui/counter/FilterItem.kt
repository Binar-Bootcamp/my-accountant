package com.binaracademy.myaccountant.ui.counter

import android.annotation.SuppressLint
import android.widget.AdapterView
import android.widget.Filter
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.ui.counter.adapter.CounterAdapter

class FilterItem(filterList: ArrayList<CounterData>, private val counterAdapter: CounterAdapter) :
    Filter() {

    private val filterlist: ArrayList<CounterData> = filterList
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint1: CharSequence? = constraint
        val results = FilterResults()

        if (constraint1 != null && constraint1.isEmpty()) {
            constraint1 = constraint1.toString().uppercase()
            val filterCounterData = ArrayList<CounterData>()
            for (i in filterlist.indices) {
                if (filterlist[i].nama.uppercase().contains(constraint1)) {
                    filterCounterData.add(filterlist[i])
                }
            }
            results.count = filterCounterData.size
            results.values = filterCounterData
        } else {
            results.count = filterlist.size
            results.values = filterlist
        }
        return results

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//       val counterAdapter = CounterAdapter()
//        counterAdapter.items = results?.values as ArrayList<CounterData>
//        counterAdapter.notifyDataSetChanged()

    }
}