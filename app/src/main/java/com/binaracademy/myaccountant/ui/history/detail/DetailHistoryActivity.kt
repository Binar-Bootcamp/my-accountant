package com.binaracademy.myaccountant.ui.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.adapter.DetailHistoryAdapter
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.presenter.DetailHistoryPresenter
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.ActivityDetailHistoryBinding
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import java.text.DateFormatSymbols
import java.util.*

class DetailHistoryActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailHistoryBinding
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	private val presenter = DetailHistoryPresenter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val summary: Summary? = intent.getParcelableExtra("history")
		binding.imgBack.setOnClickListener { finish() }
		setupSummaryView(summary)
		setupRecyclerView(summary)
	}

	private fun setupSummaryView(summary: Summary?) {
		if (summary != null) {
			val monthYear = summary.id.split("-")

			val monthNames: Array<String> = DateFormatSymbols(Locale("id", "ID")).months
			val monthName = monthNames[monthYear.first().toInt()]
			binding.tvMonthYear.text =
				resources.getString(R.string.month_year, monthName, monthYear[1])
			val saving = when (summary.type) {
				UserType.LIFE_BALANCE -> summary.income * 5 / 10
				UserType.PALING_HEMAT -> summary.income * 6 / 10
				UserType.TUKANG_SHOPPING -> summary.income * 3 / 10
			}

			binding.tvSavingAmount.text = NumberFormatter.formatRupiah(saving)
			binding.tvExpenseAmount.text = NumberFormatter.formatRupiah(summary.expense)
			binding.tvRemainingBudget.text = NumberFormatter.formatRupiah(summary.total)
		}
	}

	private fun setupRecyclerView(summary: Summary?) {
		list.addAll(emptyList())
		val layoutManager = LinearLayoutManager(this)
		recyclerView = binding.rvHistory
		val adapter = DetailHistoryAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter

		val monthYear = summary?.id?.split("-")
		if (monthYear != null) {
			presenter.getHistoryTransaction(
				monthYear.first().toInt(),
				monthYear[1].toInt()
			).observe(this){
				adapter.updateHistory(it)
			}
		}
	}
}