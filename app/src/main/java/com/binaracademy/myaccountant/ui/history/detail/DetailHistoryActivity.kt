package com.binaracademy.myaccountant.ui.history.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.adapter.DetailHistoryAdapter
import com.binaracademy.myaccountant.data.presenter.DetailHistoryPresenter
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {
	private lateinit var binding: ActivityDetailHistoryBinding
	private lateinit var recyclerView: RecyclerView
	private var list: ArrayList<Transaction> = arrayListOf()
	
	private val presenter = DetailHistoryPresenter()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		setupRecyclerView()
	}
	
	private fun setupRecyclerView() {
		list.addAll(emptyList())
		val layoutManager = LinearLayoutManager(this)
		recyclerView = binding.rvHistory
		val adapter = DetailHistoryAdapter(list)
		presenter.getHistoryTransaction().observe(this){
			adapter.updateHistory(it)
		}
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
	}
}