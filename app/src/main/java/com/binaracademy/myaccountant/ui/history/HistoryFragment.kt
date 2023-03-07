package com.binaracademy.myaccountant.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.adapter.ListTransactionAdapter
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.data.model.TransactionsData
import com.binaracademy.myaccountant.databinding.FragmentHistoryBinding
import com.binaracademy.myaccountant.databinding.FragmentServicesBinding
import com.binaracademy.myaccountant.ui.counter.adapter.AdapterItem

//
@Suppress("UNREACHABLE_CODE")
class HistoryFragment : Fragment() {
	private lateinit var binding : FragmentHistoryBinding
	private lateinit var recyclerView : RecyclerView
	private var list : ArrayList<Transaction> = arrayListOf()
	
	
	override fun onCreateView(
		inflater : LayoutInflater , container : ViewGroup? ,
		savedInstanceState : Bundle?
	) : View? {
		// Inflate the layout for this fragment
		binding = FragmentHistoryBinding.inflate(inflater , container , false)
		return binding.root
		list.addAll(TransactionsData.listData)
		
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = binding.rvHistory
		val adapter = HistoryAdapter(list)
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
		return view
		
		
	}
	
	
}