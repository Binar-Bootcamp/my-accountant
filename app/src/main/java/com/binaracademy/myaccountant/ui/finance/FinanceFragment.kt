package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.data.model.TransactionsData
import com.binaracademy.myaccountant.data.adapter.ListTransactionAdapter
import com.binaracademy.myaccountant.databinding.FragmentFinanceBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.util.helpers.intentTo


class FinanceFragment : Fragment() {
	private lateinit var binding: FragmentFinanceBinding
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		binding = FragmentFinanceBinding.inflate(inflater, container, false)
		
		list.addAll(TransactionsData.listData)
		
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = binding.rvTransaction
		
		val adapter = ListTransactionAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		binding.fabAdd.setOnClickListener {
			binding.root.context.intentTo(AddFinanceActivity::class.java)
		}
	}
}