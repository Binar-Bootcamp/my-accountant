package com.binaracademy.myaccountant.ui.laundry.adapter

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
import com.binaracademy.myaccountant.databinding.LaundryListBinding
import com.binaracademy.myaccountant.ui.laundry.model.LaundryModel
import com.bumptech.glide.Glide
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

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

    private lateinit var binding : LaundryListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : HolderItem{
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.laundry_list, parent ,false)
        val binding = LaundryListBinding.bind(view)
        return HolderItem(binding.root)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder : HolderItem, position : Int) {
        val model = itemList[position]
        val nama = model.nama
        val url = model.url
        val location = model.location
        Glide.with(binding.root.context)
            .load(url)
            .into(holder.imgLaundry)
        holder.tvNama.text = nama
        holder.tv_loc.text = location
        val currencyFormat : NumberFormat = NumberFormat.getCurrencyInstance(Locale("id" , "ID"))
        holder.tv_price.text = currencyFormat.format(model.price)

    }

    inner class HolderItem(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var imgLaundry : ImageView
        var tvNama : TextView
        var tv_loc : TextView
        var tv_price : TextView

        init {
            binding = LaundryListBinding.bind(itemView)
            tv_price = binding.tvPriceLaundry
            imgLaundry = binding.imageLaundry
            tvNama = binding.tvTitleLaundry
            tv_loc = binding.tvLocationLaundry


        }
    }

    override fun getFilter() : Filter {
        if (filter == null) {
            filter = FilterLaundry(filterList , this)
        }
        return filter as FilterLaundry
    }
}