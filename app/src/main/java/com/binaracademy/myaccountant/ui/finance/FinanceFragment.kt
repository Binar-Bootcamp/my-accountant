package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
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
import com.binaracademy.myaccountant.data.presenter.AllTransactionContract
import com.binaracademy.myaccountant.data.presenter.AllTransactionPresenter
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.FragmentFinanceBinding
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import com.binaracademy.myaccountant.util.helpers.intentTo
import kotlinx.coroutines.launch
import java.util.*

class FinanceFragment : Fragment(), AllTransactionContract.View {
	private val presenter = AllTransactionPresenter()
	private lateinit var binding: FragmentFinanceBinding
	private lateinit var recyclerView: RecyclerView
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		val calendar = Calendar.getInstance()
		val id = "${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)}"
		binding = FragmentFinanceBinding.inflate(inflater, container, false)
		
		presenter.setView(this)
		lifecycleScope.launch {
			presenter.initialFetchDataSummary(id)
		}
		
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
		
		val adapter = ListTransactionAdapter(arrayListOf())
		presenter.getAllTransactions().observe(viewLifecycleOwner) {
			adapter.updateTransaction(it)
		}
		adapter.setOnItemClickCallback(object : ListTransactionAdapter.OnItemClickCallback {
			override fun onItemClick(data: Transaction) {
			
			}
		})
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		
		super.onViewCreated(view, savedInstanceState)
		
		binding.fabAdd.setOnClickListener {
			binding.root.context.intentTo(AddFinanceActivity::class.java)
		}
	}
	
	override fun onUpdatedSummarySuccess(summary: Summary) {
		binding.tvIncomeAmount.text = NumberFormatter.formatRupiah(summary.income)
		binding.tvExpenseAmount.text = NumberFormatter.formatRupiah(summary.expense)
		binding.tvBudgetAmount.text = NumberFormatter.formatRupiah(summary.budget)
		binding.tvTotalAmount.text = NumberFormatter.formatRupiah(summary.total)
	}
}