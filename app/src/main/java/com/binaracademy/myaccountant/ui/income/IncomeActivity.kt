package com.binaracademy.myaccountant.ui.income

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.FragmentIncomeBinding
import java.text.NumberFormat

class IncomeActivity : AppCompatActivity() {

    lateinit var binding : FragmentIncomeBinding

    val editText: EditText = findViewById(R.id.input_income)

    val rupiah = NumberFormat.getCurrencyInstance()

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

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){

            }

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isNotEmpty()){
                    val amount = s.toString().toDouble()
                    val formattedAmount = rupiah.format(amount)

                    editText.setText(formattedAmount)
                    editText.setSelection(formattedAmount.length)
                }
            }
        })
    }
}