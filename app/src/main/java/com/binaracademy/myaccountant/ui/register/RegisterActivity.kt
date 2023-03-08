package com.binaracademy.myaccountant.ui.register

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityRegisterBinding
import com.binaracademy.myaccountant.ui.income.IncomeActivity
import com.binaracademy.myaccountant.util.helpers.intentTo

class RegisterActivity : AppCompatActivity() {
	
	private val binding: ActivityRegisterBinding by lazy {
		ActivityRegisterBinding.inflate(layoutInflater)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		val items = listOf("Si Life Balance", "Si Paling Hemat", "Si Tukang Shopping")
		val adapter = ArrayAdapter(this, R.layout.dropdown_items, items)
		binding.savingList.setAdapter(adapter)
		
		val shared = getSharedPreferences("prefData", MODE_PRIVATE)
		val editor = shared.edit()
		
		binding.apply {
			btnContinue.setOnClickListener {
				val name = inputNama.text.toString()
				val saving = savingList.text.toString()
				
				editor.apply {
					putString("username", name)
					putString("saving_type", saving)
					apply()
				}
				intentTo(IncomeActivity::class.java)
			}
		}
	}
}