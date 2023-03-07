package com.binaracademy.myaccountant.ui.income

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.FragmentIncomeBinding

class IncomeActivity : AppCompatActivity() {

    lateinit var binding : FragmentIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shared = getSharedPreferences("prefData", MODE_PRIVATE)
        val editor = shared.edit()

        binding.apply {
            btnSave.setOnClickListener {
                val income = inputIncome.text.toString()

                editor.apply {
                    putString("gaji", income)
                    apply()
                }
            }
        }
    }
}