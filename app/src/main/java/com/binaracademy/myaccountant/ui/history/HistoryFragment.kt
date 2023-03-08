package com.binaracademy.myaccountant.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.data.model.TransactionsData
import com.binaracademy.myaccountant.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
	private lateinit var binding: FragmentHistoryBinding
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentHistoryBinding.inflate(inflater, container, false)
		
		setupRecyclerView()
		
		return binding.root
		
		
	}
	
	private fun setupRecyclerView() {
		list.addAll(TransactionsData.listData)
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = binding.rvHistory
		val adapter = HistoryAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
	}
}