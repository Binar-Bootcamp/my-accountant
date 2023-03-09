package com.binaracademy.myaccountant.ui.laundry.adapter

import android.content.Context
import android.content.SyncAdapterType
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.databinding.FragmentLaundryBinding
import com.binaracademy.myaccountant.ui.counter.adapter.AdapterItem
import com.binaracademy.myaccountant.ui.laundry.mode.LaundryModel

class AdapterLaundry : RecyclerView.Adapter<AdapterLaundry.HolderItem>, Filterable {
    private  var context : Context
    var itemList : ArrayList<LaundryModel>
    private var filterList : ArrayList<LaundryModel>
    private var filter : FilterLaundry? = null

    constructor(context: Context, itemList: ArrayList<LaundryModel>) : super(){
        context.also { this.context = it }
        this.itemList = itemList
        this.filterList = itemList
    }

    private lateinit var binding : FragmentLaundryBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AdapterItem.HolderItem

}