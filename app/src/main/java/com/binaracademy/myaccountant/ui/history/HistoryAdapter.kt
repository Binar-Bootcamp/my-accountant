package com.binaracademy.myaccountant.ui.history
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.databinding.ChildHistoryBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


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
		val model = listTransaction[position]
		val bulan = model.date
		val currencyFormat : NumberFormat = NumberFormat.getCurrencyInstance(Locale("id" , "ID"))
		holder.grandTotal.text = currencyFormat.format(model.amount)
		holder.totall.text = currencyFormat.format(model.amount)
		holder.bulan.text = bulan
		
		
		
		
	}
	
	inner class HistoryViewHolder(val binding : ChildHistoryBinding) :
		RecyclerView.ViewHolder(binding.root) {
		
		var bulan : TextView = binding.tvChildDate
		var totall : TextView = binding.tvChildTotal
		var grandTotal : TextView = binding.tvChildAmount
		
	}
}