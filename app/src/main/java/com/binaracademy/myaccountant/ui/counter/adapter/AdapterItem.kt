package com.binaracademy.myaccountant.ui.counter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.CounterChildBinding
import com.binaracademy.myaccountant.ui.counter.model.ModelItem
import com.bumptech.glide.Glide

class AdapterItem : RecyclerView.Adapter<AdapterItem.HolderItem> , Filterable {
	private var context : Context
	var itemList : ArrayList<ModelItem>
	private var filterList : ArrayList<ModelItem>
	private var filter : FilterItem? = null
	
	constructor(context : Context , itemList : ArrayList<ModelItem>) : super() {
		context.also { this.context = it }
		this.itemList = itemList
		this.filterList = itemList
	}
	
	private lateinit var binding : CounterChildBinding
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : HolderItem {
		
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.counter_child , parent , false)
		val binding = CounterChildBinding.bind(view)
		return HolderItem(binding.root)
	}
	
	override fun getItemCount() : Int {
		return itemList.size
	}
	
	override fun onBindViewHolder(holder : HolderItem , position : Int) {
		val currency = "IDR "
		
		val model = itemList[position]
		
		val nama = model.nama
		val url = model.url
		val location = model.location
		val price = model.price.toString()
		
		Glide.with(binding.root.context)
			.load(url)
			.into(holder.imgCounter)
		holder.tvNama.text = nama
		holder.Tvloc.text = location
		holder.tvPrice.text = buildString {
			append(currency)
			append(price)
		}
		
		
	}
	
	inner class HolderItem(itemView : View) : RecyclerView.ViewHolder(itemView) {
		
		var imgCounter : ImageView
		var tvNama : TextView
		var Tvloc : TextView
		var tvPrice : TextView
		
		init {
			binding = CounterChildBinding.bind(itemView)
			tvPrice = binding.textViewPrice
			imgCounter = binding.imageCounter
			tvNama = binding.textViewTitle
			Tvloc = binding.textLocation
			
			
		}
	}
	
	override fun getFilter() : Filter {
		if (filter == null) {
			filter = FilterItem(filterList , this)
		}
		return filter as FilterItem
	}
	
	
}