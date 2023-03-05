package com.binaracademy.myaccountant.ui.counter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.widget.*
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityCounterBinding
import com.binaracademy.myaccountant.ui.counter.adapter.AdapterItem
import com.binaracademy.myaccountant.ui.counter.model.ModelItem


class CounterActivity : AppCompatActivity() {
	private lateinit var binding : ActivityCounterBinding
	
	//	private val lisAdapter = CounterAdapter()
	private lateinit var itemList : ArrayList<ModelItem>
	private lateinit var adapterItem : AdapterItem
	
	private val namas = arrayOf(
		"Mendoan Istana" ,
		"Ketoprak pak Toro" ,
		"Bakso malang Joko" ,
		"Gorengan Serba Gopek" ,
		"Tahu Genjrot Pak Supri" ,
		"Kembar Parfume" ,
		"Rumah Parfume" ,
		"Fome Ponsel" ,
		"Cheese Tea" ,
		"Susu Mbok Darmi"
	
	)
	private val urls = arrayOf(
		"https://www.franchiseglobal.com/images/posts/2020/09/08/mendoan-istana.JPG" ,
		"https://data.spektakel.id/articles/Ketoprak%20Toro%201.jpg" ,
		"https://cdns.klimg.com/merdeka.com/i/w/news/2016/11/10/777169/670x335/potensi-usaha-menjanjikan-miwon-sebar-300-gerobak-bakso.jpg" ,
		"https://2.bp.blogspot.com/-WvAQ2pw_tMg/VwIvaGbwLNI/AAAAAAAAEbQ/pOr24kTzfcACVaQzGF234uPUeNKXqrX-A/s1600/Bisnis%2BGorengan.jpg" ,
		"https://akcdn.detik.net.id/community/media/visual/2020/08/08/rekomendasi-tahu-gejrot-pedas.jpeg?w=700&q=90" ,
		"https://www.liputanbmr.com/wp-content/uploads/2021/10/IMG_20211028_215048-620x330.jpg" ,
		"https://scontent-sin6-2.xx.fbcdn.net/v/t1.18169-9/167008_10150089110892360_5193948_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=cdbe9c&_nc_ohc=MJVITU7Uq8QAX9Vsx5e&_nc_ht=scontent-sin6-2.xx&oh=00_AfAUieRb3kYC_NLByoNFRKtSPKOyHH5IXnBlZrXbexyjgw&oe=64298B24" ,
		"https://cdn-2.tstatic.net/lampung/foto/bank/images/pemuda-di-tuba-lampung-raup-ratusan-juta-per-bulan.jpg" ,
		"https://image.cermati.com/q_70/svzpy2gnrrkdqzxb0que.webp" ,
		"https://garuda.industry.co.id/uploads/berita/detail/47934.jpg"
	)
	private val locations = arrayOf(
		"Jl. Kemerdekaan, no.22" ,
		"Jl. Gang AB" ,
		"Jl. Assirot 22" ,
		"Kesehatan. No.33" ,
		"Jl. cidodol. No.33" ,
		"jl. H.Gareng. No.45A " ,
		"Jl. Kebayoran lama" ,
		"jl. H.Gareng. No.45A" ,
		"Mall Gandaria City" ,
		"Mall Gandaria City blok.33A"
	
	)
	
	override fun onCreate(savedInstanceState : Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCounterBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)
		binding.toolbar.inflateMenu(R.menu.menu_sort)
		
		setupRecyclerView()
		
	}
	
	override fun onOptionsItemSelected(item : MenuItem) : Boolean {
		val id = item.itemId
		if (id == R.id.action_sort) {
			sortDialog()
		}
		return super.onOptionsItemSelected(item)
	}
	
	private fun sortDialog() {
		val options = arrayOf("Ascending" , "Descending")
		val dialog = AlertDialog.Builder(this)
		dialog.setTitle("Sort").setItems(options) { dialogInterface , i ->
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
		itemList.sortByDescending { it.nama }
		adapterItem.notifyDataSetChanged()
	}
	
	@SuppressLint("NotifyDataSetChanged")
	private fun sortAscending() {
		itemList.sortBy { it.nama }
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
		val lm = GridLayoutManager(this , 2)
		binding.rvOne.layoutManager = lm
		itemList = ArrayList()
		for (i in namas.indices) {
			val modell = ModelItem(urls[i] , namas[i] , locations[i])
			itemList.add(modell)
		}
		adapterItem = AdapterItem(this , itemList)
		binding.rvOne.adapter = adapterItem
		
	}
	
}


