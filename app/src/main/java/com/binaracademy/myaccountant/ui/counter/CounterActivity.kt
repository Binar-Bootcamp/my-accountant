package com.binaracademy.myaccountant.ui.counter

import android.R
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.data.CounterObject
import com.binaracademy.myaccountant.databinding.ActivityCounterBinding
import com.binaracademy.myaccountant.ui.counter.adapter.CounterAdapter
import java.util.*

class CounterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCounterBinding
    private val lisAdapter = CounterAdapter()


    var price =
        arrayOf("", "Rp. 1,000 - Rp. 20,000 ", "Rp. 20,001 - Rp. 50,000", "> Rp. 50.000")
    val NEW_SPINNER_ID = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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