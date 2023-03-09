package com.binaracademy.myaccountant.ui.register

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityRegisterBinding
import com.binaracademy.myaccountant.ui.income.IncomeActivity
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager
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
		
		val userTable = Global.USER_TABLE
		val username = Global.USERNAME
		val type = Global.TYPE
		
		val sharedPreferences = SharedPreferencesManager(this, userTable)
		
		binding.apply {
			btnContinue.setOnClickListener {
				val name = inputNama.text.toString()
				val saving = savingList.text.toString()
				
				sharedPreferences.putString(username, name)
				sharedPreferences.putString(type, saving)
				intentTo(IncomeActivity::class.java)
			}
		}
		
		binding.root.setOnClickListener {
			hideSoftKeyboard(this)
		}
	}
	
	private fun hideSoftKeyboard(activity: Activity) {
		val inputMethodManager = activity.getSystemService(
			INPUT_METHOD_SERVICE
		) as InputMethodManager
		if (inputMethodManager.isAcceptingText) {
			inputMethodManager.hideSoftInputFromWindow(
				activity.currentFocus!!.windowToken,
				0
			)
		}
	}
}