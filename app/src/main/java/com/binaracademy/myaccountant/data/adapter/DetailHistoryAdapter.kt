package com.binaracademy.myaccountant.data.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.ItemDetailHistoryBinding
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import com.binaracademy.myaccountant.util.helpers.formatString

class DetailHistoryAdapter(private val listTransaction: ArrayList<Transaction>) :
	RecyclerView.Adapter<DetailHistoryAdapter.ListViewHolder>() {
	
	private lateinit var binding: ItemDetailHistoryBinding
	
	@SuppressLint("NotifyDataSetChanged")
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
		val view =
			ItemDetailHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ListViewHolder(view)
	}
	
	@RequiresApi(Build.VERSION_CODES.O)
	override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
		val transaction = listTransaction[position]
		
		holder.tvName.text = transaction.description
		holder.tvDate.text = transaction.createdAt.formatString("d MMMM yyyy")
		holder.tvAmount.text = NumberFormatter.formatRupiah(transaction.amount)
		if (transaction.type == TransactionType.INCOME){
			holder.tvAmount.setTextColor(Color.parseColor("#00C844"))
		} else {
			holder.tvAmount.setTextColor(Color.parseColor("#C80000"))
		}
	}
	
	@SuppressLint("NotifyDataSetChanged")
	fun updateHistory(transactions: List<Transaction>) {
		this.listTransaction.clear()
		this.listTransaction.addAll(transactions)
		notifyDataSetChanged()
	}
	
	override fun getItemCount(): Int {
		return listTransaction.size
	}
	
	inner class ListViewHolder(val binding : ItemDetailHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
		var tvName: TextView = binding.tvName
		var tvDate: TextView = binding.tvDate
		var tvAmount: TextView = binding.tvAmount
	}
}
