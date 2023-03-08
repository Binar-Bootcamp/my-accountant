package com.binaracademy.myaccountant.data.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.room.Transaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

class ListTransactionAdapter(private val listTransaction: ArrayList<Transaction>) :
	RecyclerView.Adapter<ListTransactionAdapter.ListViewHolder>() {

	private lateinit var onItemClickCallback: OnItemClickCallback

	@SuppressLint("NotifyDataSetChanged")
	fun updateTransaction(transactions: List<Transaction>) {
		this.listTransaction.clear()
		this.listTransaction.addAll(transactions)
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
		val view: View =
			LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
		return ListViewHolder(view)
	}

	private fun getTransactionIcon(transactionType: TransactionType): Int {
		return when (transactionType) {
			TransactionType.INCOME -> {
				Transaction.INCOME[ThreadLocalRandom.current().nextInt(0, Transaction.INCOME.size)]
			}
			TransactionType.OUTCOME -> {
				Transaction.EXPENSE[ThreadLocalRandom.current().nextInt(0, Transaction.EXPENSE.size)]
			}
		}
	}

	@RequiresApi(Build.VERSION_CODES.O)
	override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
		val transaction = listTransaction[position]

		transaction.type

		Glide.with(holder.itemView.context)
			.load(getTransactionIcon(transaction.type))
			.apply(RequestOptions().override(55, 55))
			.into(holder.imgPhoto)

		holder.tvCategory.text = transaction.source
		holder.tvDescriptions.text = transaction.description
		val dateFormat = SimpleDateFormat("MMMM yyyy", Locale("id", "ID"))
		val formattedDate = dateFormat.format(transaction.createdAt)
		holder.tvDate.text = formattedDate
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
