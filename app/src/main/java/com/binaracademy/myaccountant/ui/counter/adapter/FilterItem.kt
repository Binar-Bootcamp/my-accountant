package com.binaracademy.myaccountant.ui.counter.adapter

import android.annotation.SuppressLint
import android.widget.Filter
import com.binaracademy.myaccountant.ui.counter.model.ModelItem


class FilterItem(filterList : ArrayList<ModelItem> , private val adapterItem : AdapterItem) :
	Filter() {
	
	private val filterList : ArrayList<ModelItem> = filterList
	override fun performFiltering(constraint : CharSequence?) : FilterResults {
		var constraint1 : CharSequence? = constraint
		val result = FilterResults()
		
		if (constraint1 != null && constraint1.isNotEmpty()) {
			constraint1 = constraint1.toString().uppercase()
			val filteredModels = ArrayList<ModelItem>()
			for (i in filterList.indices) {
				if (filterList[i].nama.uppercase().contains(constraint1)) {
					filteredModels.add(filterList[i])
				}
			}
			result.count = filteredModels.size
			result.values = filteredModels
		} else {
			result.count = filterList.size
			result.values = filterList
		}
		return result
	}
	
	@SuppressLint("NotifyDataSetChanged")
	override fun publishResults(constraint : CharSequence , results : FilterResults) {
		adapterItem.itemList = results.values as ArrayList<ModelItem>
		adapterItem.notifyDataSetChanged()
	}
}