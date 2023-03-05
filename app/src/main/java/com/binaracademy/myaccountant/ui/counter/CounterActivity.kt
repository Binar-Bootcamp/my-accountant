package com.binaracademy.myaccountant.ui.counter

import android.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.data.CounterObject
import com.binaracademy.myaccountant.databinding.ActivityCounterBinding
import com.binaracademy.myaccountant.ui.counter.adapter.CounterAdapter
import java.util.*
import kotlin.collections.ArrayList

class CounterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCounterBinding
    private val lisAdapter = CounterAdapter()

    private lateinit var itemList: ArrayList<CounterData>


    var price =
        arrayOf("", "Rp. 1,000 - Rp. 20,000 ", "Rp. 20,001 - Rp. 50,000", "> Rp. 50.000")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.inflateMenu(com.binaracademy.myaccountant.R.menu.menu_sort)


        binding.rvOne.layoutManager = GridLayoutManager(this, 2)
        binding.rvOne.adapter = lisAdapter
        val newItem = CounterObject.list
        lisAdapter.addItems(newItem)


        var aa = ArrayAdapter(this, R.layout.simple_spinner_item, price)
        aa.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        with(binding.mySpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@CounterActivity
            prompt = "Recommended Price"
            gravity = Gravity.CENTER

        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.binaracademy.myaccountant.R.menu.menu_sort, menu)

        val searchItem = menu?.findItem(com.binaracademy.myaccountant.R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //  counterAdapter.filter.filter(newText)
//                lisAdapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == com.binaracademy.myaccountant.R.id.action_search) {
            sortDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDialog() {
        val option = arrayOf("Asc", "Desc")
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Sort")
            .setItems(option) { dialogInterface, i ->
                if (i == 0) {
                    dialogInterface.dismiss()
                    sortAsc()
                } else if (i == 1) {
                    dialogInterface.dismiss()
                    sortDesc()
                }

            }
    }

    private fun sortAsc() {
        lisAdapter.items.sortedBy { it.nama }
    }

    private fun sortDesc() {
        lisAdapter.items.sortByDescending { it.nama }

    }

    private fun showToast(
        context: Context = applicationContext,
        message: String,
        duration: Int = Toast.LENGTH_LONG
    ) {
        Toast.makeText(context, message, duration).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (position == 0) {

        }
    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing selected")
    }

}