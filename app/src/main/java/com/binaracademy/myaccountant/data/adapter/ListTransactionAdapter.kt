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
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import com.binaracademy.myaccountant.util.helpers.formatString
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ListTransactionAdapter(private val listTransaction: ArrayList<Transaction>) :
    RecyclerView.Adapter<ListTransactionAdapter.ListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateTransaction(transactions: List<Transaction>) {
        this.listTransaction.clear()
        this.listTransaction.addAll(transactions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        return ListViewHolder(view)
    }

    private fun getTransactionIcon(transaction: Transaction): Int {
        return when (transaction.type) {
            TransactionType.INCOME -> {
                Transaction.INCOME
            }
            TransactionType.OUTCOME -> {
                when (transaction.source) {
                    "Tagihan" -> Transaction.EXPENSE[1]
                    "Makan" -> Transaction.EXPENSE[1]
                    "Hiburan" -> Transaction.EXPENSE[3]
                    "Rumah" -> Transaction.EXPENSE[1]
                    "Kendaraan" -> Transaction.EXPENSE[5]
                    "Belanja" -> Transaction.EXPENSE[2]
                    "Sosial" -> Transaction.EXPENSE[4]
                    else -> Transaction.EXPENSE[1]
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val transaction = listTransaction[position]
        Glide.with(holder.itemView.context)
            .load(getTransactionIcon(transaction))
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)

        holder.tvCategory.text = transaction.source
        holder.tvDescriptions.text = transaction.description
        holder.tvDate.text = transaction.createdAt.formatString("d MMMM yyyy")
        holder.tvAmount.text = NumberFormatter.formatRupiah(transaction.amount)
    }

    override fun getItemCount(): Int {
        return listTransaction.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategory: TextView = itemView.findViewById(R.id.tv_item_category)
        var tvDescriptions: TextView = itemView.findViewById(R.id.tv_item_description)
        var tvDate: TextView = itemView.findViewById(R.id.tv_item_date)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_item_amount)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}
