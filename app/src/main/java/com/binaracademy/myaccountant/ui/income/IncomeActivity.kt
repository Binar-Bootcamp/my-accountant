package com.binaracademy.myaccountant.ui.income

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.databinding.ActivityIncomeBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.util.helpers.intentTo

class IncomeActivity : AppCompatActivity() {
	
	private val binding: ActivityIncomeBinding by lazy {
		ActivityIncomeBinding.inflate(layoutInflater)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
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
				intentTo(MainActivity::class.java)
			}
		}
	}
}