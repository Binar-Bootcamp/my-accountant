package com.binaracademy.myaccountant.ui.laundry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityLaundryBinding
import com.binaracademy.myaccountant.ui.counter.model.CounterObj
import com.binaracademy.myaccountant.ui.laundry.adapter.AdapterLaundry
import com.binaracademy.myaccountant.ui.laundry.model.LaundryObj

class LaundryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLaundryBinding
    private val getData = LaundryObj.listData
    private lateinit var adapterLaundry: AdapterLaundry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaundryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tbLaundry)
        binding.tbLaundry.inflateMenu(R.menu.menu_sort)

        setupRecyclerView()
        binding.sortby.setOnClickListener {
            sortDialog()
        }
    }

    private fun sortDialog() {

        val options = arrayOf("Termurah - Termahal" , "Termahal - Termurah")
        val dialog = AlertDialog.Builder(this, R.style.CustomAlertDialog)
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
        adapterLaundry.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun sortAscending() {
        getData.sortBy { it.price }
        adapterLaundry.notifyDataSetChanged()
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
                adapterLaundry.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun onSupportNavigateUp() : Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvLaundry.layoutManager = layoutManager
        adapterLaundry = AdapterLaundry(this, getData)
        binding.rvLaundry.adapter = adapterLaundry
    }
}