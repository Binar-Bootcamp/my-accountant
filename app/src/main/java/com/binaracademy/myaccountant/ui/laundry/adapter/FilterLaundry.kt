package com.binaracademy.myaccountant.ui.laundry.adapter

import com.binaracademy.myaccountant.ui.laundry.model.LaundryModel
import android.widget.Filter

class FilterLaundry(filterList : ArrayList<LaundryModel>, private val adapterLaundry: AdapterLaundry) :
    Filter(){
    private val filterList : ArrayList<LaundryModel> = filterList

    override fun performFiltering(constraint : CharSequence?) : FilterResults{
        var constraint1 : CharSequence? = constraint
        val result = FilterResults()

        if(constraint1 != null && constraint1.isNotEmpty()){
            constraint1 = constraint1.toString().uppercase()
            val filteredModels = ArrayList<LaundryModel>()
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

    override fun publishResults(constraint: CharSequence, results : FilterResults){
        adapterLaundry.itemList = results.values as ArrayList<LaundryModel>
        adapterLaundry.notifyDataSetChanged()
    }
}