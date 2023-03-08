package com.binaracademy.myaccountant.ui.history

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.model.Transaction
import com.binaracademy.myaccountant.data.model.TransactionsData
import com.binaracademy.myaccountant.databinding.FragmentHistoryBinding


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
	
	override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
		
		binding.toolbarIcon.setOnClickListener { findNavController().popBackStack()
		}
		
		
		super.onViewCreated(view , savedInstanceState)
	}
	
	@Deprecated("Deprecated in Java")
	override fun onCreateOptionsMenu(menu : Menu , inflater : MenuInflater) {
	
	
	}
	
	
}