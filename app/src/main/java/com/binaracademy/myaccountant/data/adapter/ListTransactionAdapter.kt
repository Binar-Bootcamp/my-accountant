package com.binaracademy.myaccountant.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.Transaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListTransactionAdapter(private val listTransaction: ArrayList<Transaction>) :
	RecyclerView.Adapter<ListTransactionAdapter.ListViewHolder>() {

	private lateinit var onItemClickCallback: OnItemClickCallback

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
		return ListViewHolder(view)
	}

	override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
		val transaction = listTransaction[position]

		Glide.with(holder.itemView.context)
			.load(transaction.photo)
			.apply(RequestOptions().override(55, 55))
			.into(holder.imgPhoto)

		holder.tvCategory.text = transaction.category
		holder.tvDescriptions.text = transaction.description
		holder.tvDate.text = transaction.date
		holder.tvAmount.text = transaction.amount.toString()
		holder.itemView.setOnClickListener { onItemClickCallback.onItemClick(listTransaction[holder.adapterPosition]) }
	}

	override fun getItemCount(): Int {
		return listTransaction.size
	}

	fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
		this.onItemClickCallback = onItemClickCallback
	}

	interface OnItemClickCallback {
		fun onItemClick(data: Transaction)
	}

	inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		var tvCategory: TextView = itemView.findViewById(R.id.tv_item_category)
		var tvDescriptions: TextView = itemView.findViewById(R.id.tv_item_description)
		var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
		var tvAmount: TextView = itemView.findViewById(R.id.tv_item_amount)
		var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
	}
}
