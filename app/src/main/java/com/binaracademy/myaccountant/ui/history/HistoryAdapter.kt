package com.binaracademy.myaccountant.ui.history
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.ChildHistoryBinding
import com.binaracademy.myaccountant.util.helpers.formatString
import java.util.*
import kotlin.collections.ArrayList


@Suppress("UNREACHABLE_CODE")
class HistoryAdapter(
	private val listTransaction: ArrayList<Transaction>,
	private val historyContext: Context
) :
	RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

	override fun onCreateViewHolder(parent : ViewGroup , viewType : Int) : HistoryViewHolder {
		val view = ChildHistoryBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
		return HistoryViewHolder(view)
	}
	
	override fun getItemCount() : Int {
		return listTransaction.size
	}
	
	override fun onBindViewHolder(holder : HistoryViewHolder , position : Int) {
		val transaction = listTransaction[position]
		holder.grandTotal.text = historyContext.resources.getString(R.string.currency_amount, transaction.amount)
		holder.totall.text =  historyContext.resources.getString(R.string.currency_amount, transaction.amount)
		holder.bulan.text = transaction.createdAt.formatString("MMMM yyyy")
	}

	@SuppressLint("NotifyDataSetChanged")
	fun updateHistory(transactions: List<Transaction>) {
		this.listTransaction.clear()
		this.listTransaction.addAll(transactions)
		notifyDataSetChanged()
	}

	inner class HistoryViewHolder(val binding : ChildHistoryBinding) :
		RecyclerView.ViewHolder(binding.root) {
		var bulan : TextView = binding.tvChildDate
		var totall : TextView = binding.tvChildTotal
		var grandTotal : TextView = binding.tvChildAmount
	}
}