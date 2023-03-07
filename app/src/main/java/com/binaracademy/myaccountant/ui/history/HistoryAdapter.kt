package com.binaracademy.myaccountant.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.databinding.ChildHistoryBinding

@Suppress("UNREACHABLE_CODE")
class HistoryAdapter(private val listTransaction : ArrayList<Transaction>) :
	RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
	
	
	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : HistoryViewHolder {
		val view = ChildHistoryBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
		return HistoryViewHolder(view)
		
		
	}
	
	override fun getItemCount() : Int {
		return listTransaction.size
	}
	
	override fun onBindViewHolder(holder : HistoryViewHolder , position : Int) {
		holder.binding.tvChildDate.text = listTransaction[position].date
		holder.binding.tvChildAmount.text= listTransaction[position].amount.toString()
		holder.binding.tvChildTotal.text = listTransaction[position].amount.toString()
	}
	
	inner class HistoryViewHolder( val binding : ChildHistoryBinding) :
		RecyclerView.ViewHolder(binding.root) {
			
		
	}
}