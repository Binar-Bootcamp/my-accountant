package com.binaracademy.myaccountant.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.data.presenter.HistoryContract
import com.binaracademy.myaccountant.data.presenter.HistoryPresenter
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment(), HistoryContract.View {
	private lateinit var binding: FragmentHistoryBinding
	private lateinit var recyclerView: RecyclerView
	private val presenter = HistoryPresenter()
	private var list: ArrayList<Transaction> = arrayListOf()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHistoryBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.setView(this)
		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		list.addAll(emptyList())
		val layoutManager = LinearLayoutManager(activity)
		recyclerView = binding.rvHistory
		val adapter = HistoryAdapter(list, requireContext())
		presenter.getHistoryTransaction().observe(viewLifecycleOwner){
			adapter.updateHistory(it)
		}
		recyclerView.layoutManager = layoutManager
		recyclerView.setHasFixedSize(true)
		recyclerView.adapter = adapter
	}
}