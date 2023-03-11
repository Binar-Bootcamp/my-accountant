package com.binaracademy.myaccountant.ui.history

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.databinding.ChildHistoryBinding
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import java.text.DateFormatSymbols
import java.util.*


@Suppress("UNREACHABLE_CODE")
class HistoryAdapter(
    private val summaries: ArrayList<Summary>,
    private val historyContext: Context
) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = ChildHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return summaries.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val summary = summaries[position]
        holder.grandTotal.text = NumberFormatter.formatRupiah(summary.budget)
        holder.totall.text = NumberFormatter.formatRupiah(summary.total)
        val monthYear = summary.id.split("-")

        val monthNames: Array<String> = DateFormatSymbols(Locale("id", "ID")).months
        val monthName = monthNames[monthYear.first().toInt()]

        holder.bulan.text = historyContext.getString(R.string.month_year, monthName, monthYear[1])
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClick(summaries[holder.adapterPosition]) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateHistory(summaries: List<Summary>) {
        this.summaries.clear()
        this.summaries.addAll(summaries)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClick(data: Summary)
    }

    inner class HistoryViewHolder(val binding: ChildHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var bulan: TextView = binding.tvChildDate
        var totall: TextView = binding.tvChildTotal
        var grandTotal: TextView = binding.tvChildAmount
    }
}