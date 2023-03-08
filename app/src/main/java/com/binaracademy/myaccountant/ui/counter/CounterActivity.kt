package com.binaracademy.myaccountant.ui.counter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityCounterBinding
import com.binaracademy.myaccountant.ui.counter.adapter.AdapterItem
import com.binaracademy.myaccountant.ui.counter.model.CounterObj
import com.binaracademy.myaccountant.util.helpers.Global
import java.text.NumberFormat
import java.util.*


class CounterActivity : AppCompatActivity() {
	private lateinit var binding : ActivityCounterBinding
	private val getData = CounterObj.listData
	private lateinit var adapterItem : AdapterItem
	
	
	override fun onCreate(savedInstanceState : Bundle?) {
		
		val appName = Global.APP_TABLE
		val isFirst = Global.IS_FIRST
		
		super.onCreate(savedInstanceState)
		binding = ActivityCounterBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		binding.toolbar.inflateMenu(R.menu.menu_sort)
		
		setupRecyclerView()
		binding.logoDropdown.setOnClickListener {
			sortDialog()
		}
		
		
	}
	
	
	private fun sortDialog() {
		
		val options = arrayOf("Cheapest-Expensive" , "Expensive-Cheapest")
		val dialog = AlertDialog.Builder(this , R.style.CustomAlertDialog)
		dialog.setTitle("Sort By").setItems(options) { dialogInterface , i ->
			if (i == 0) {
				dialogInterface.dismiss()
				sortAscending()
				
			} else if (i == 1) {
				dialogInterface.dismiss()
				sortDescending()
			}
			
		}.show()
	}
	
	@SuppressLint("NotifyDataSetChanged")
	private fun sortDescending() {
		getData.sortByDescending { it.price }
		adapterItem.notifyDataSetChanged()
	}
	
	@SuppressLint("NotifyDataSetChanged")
	private fun sortAscending() {
		getData.sortBy { it.price }
		adapterItem.notifyDataSetChanged()
	}
	
	override fun onCreateOptionsMenu(menu : Menu?) : Boolean {
		
		menuInflater.inflate(R.menu.menu_sort , menu)
		val searchItem = menu?.findItem(R.id.action_search)
		val searchView = searchItem?.actionView as SearchView
		searchView.queryHint = "Search"
		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query : String?) : Boolean {
				return false
			}
			
			override fun onQueryTextChange(newText : String?) : Boolean {
				adapterItem.filter.filter(newText)
				return false
			}
			
		})
		
		return super.onCreateOptionsMenu(menu)
	}
	
	
	override fun onSupportNavigateUp() : Boolean {
		onBackPressedDispatcher.onBackPressed()
		return true
	}
	
	
	private fun setupRecyclerView() {
		val layoutManager = GridLayoutManager(this , 2)
		binding.rvOne.layoutManager = layoutManager
		adapterItem = AdapterItem(this , getData)
		binding.rvOne.adapter = adapterItem
		
	}
	
}


