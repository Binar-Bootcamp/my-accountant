package com.binaracademy.myaccountant.ui.counter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.CounterChildBinding
import com.binaracademy.myaccountant.ui.counter.model.ModelItem
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.RecyclerView as RecyclerView1

class AdapterItem : RecyclerView1.Adapter<AdapterItem.HolderItem> , Filterable {
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
		val model = itemList[position]
		val nama = model.nama
		val url = model.url
		val location = model.location
		Glide.with(binding.root.context)
			.load(url)
			.into(holder.imgCounter)
		holder.tvNama.text = nama
		holder.Tvloc.text = location
		val currencyFormat : NumberFormat = NumberFormat.getCurrencyInstance(Locale("id" , "ID"))
		holder.tvPrice.text = currencyFormat.format(model.price)
		
	}
	
	inner class HolderItem(itemView : View) : RecyclerView1.ViewHolder(itemView) {
		
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