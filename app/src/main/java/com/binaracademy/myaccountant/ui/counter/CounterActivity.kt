package com.binaracademy.myaccountant.ui.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binaracademy.myaccountant.data.CounterData
import com.binaracademy.myaccountant.data.CounterObject
import com.binaracademy.myaccountant.databinding.ActivityCounterBinding
import com.binaracademy.myaccountant.ui.counter.adapter.CounterAdapter

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    private val lisAdapter = CounterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.rvOne.layoutManager = LinearLayoutManager(this)
            binding.rvOne.adapter = lisAdapter

            val newItem = CounterObject.list
            lisAdapter.addItems(newItem)

    }
}