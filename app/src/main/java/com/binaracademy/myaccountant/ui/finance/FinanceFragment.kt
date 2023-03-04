package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.Transaction
import com.binaracademy.myaccountant.data.TransactionsData
import com.binaracademy.myaccountant.data.adapter.ListTransactionAdapter


class FinanceFragment : Fragment() {
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(R.layout.fragment_finance, container, false)
		
		list.addAll(TransactionsData.listData)
		
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = view.findViewById(R.id.rv_transaction)
		
		val adapter = ListTransactionAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
		return view
	}
}