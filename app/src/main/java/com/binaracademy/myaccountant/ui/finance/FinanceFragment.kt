package com.binaracademy.myaccountant.ui.finance

import java.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.adapter.ListTransactionAdapter
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.data.model.TransactionsData
import com.binaracademy.myaccountant.data.presenter.AllTransactionContract
import com.binaracademy.myaccountant.data.presenter.AllTransactionPresenter
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.databinding.FragmentFinanceBinding
import com.binaracademy.myaccountant.util.helpers.intentTo
import kotlinx.coroutines.launch


class FinanceFragment : Fragment(), AllTransactionContract.View {
	private val presenter = AllTransactionPresenter()
	private lateinit var binding: FragmentFinanceBinding
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding = FragmentFinanceBinding.inflate(inflater, container, false)
		
		list.addAll(TransactionsData.listData)
		
		setUpRecycleView()
		
		binding.imgType.setOnClickListener {
			val popup = PopupMenu(this.requireActivity(), it)
			popup.inflate(R.menu.popup_menu)
			popup.show()
		}
		
		return binding.root
	}
	
	private fun setUpRecycleView() {
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = binding.rvTransaction
		
		val adapter = ListTransactionAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val calendar = Calendar.getInstance()
		val id = "${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)}"
		super.onViewCreated(view, savedInstanceState)
		presenter.setView(this)
		lifecycleScope.launch {
			presenter.initialFetchDataSummary(id)
		}
		binding.fabAdd.setOnClickListener {
			binding.root.context.intentTo(AddFinanceActivity::class.java)
		}
	}
	
	override fun onUpdatedSummarySuccess(summary: Summary) {
		binding.tvIncomeAmount.text = summary.income.toString()
		binding.tvBudgetAmount.text = resources.getString(R.string.currency_amount, summary.budget)
		binding.tvExpenseAmount.text =
			resources.getString(R.string.currency_amount, summary.expense)
		binding.tvTotalAmount.text = resources.getString(R.string.currency_amount, summary.total)
	}
}