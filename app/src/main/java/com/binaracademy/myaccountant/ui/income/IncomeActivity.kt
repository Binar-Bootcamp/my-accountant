package com.binaracademy.myaccountant.ui.income

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.presenter.InitialContract
import com.binaracademy.myaccountant.data.presenter.InitialPresenter
import com.binaracademy.myaccountant.databinding.ActivityIncomeBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager
import com.binaracademy.myaccountant.util.helpers.intentTo
import kotlinx.coroutines.launch

class IncomeActivity : AppCompatActivity(), InitialContract.View {
	
	private val presenter = InitialPresenter()
	
	private val binding: ActivityIncomeBinding by lazy {
		ActivityIncomeBinding.inflate(layoutInflater)
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		presenter.setView(this)
		
		val userTable = Global.USER_TABLE
		val type = Global.TYPE
		
		val sharedPreferences = SharedPreferencesManager(this, userTable)
		
		val userType = when (sharedPreferences.getString(type, "")) {
			"Si Life Balance" -> UserType.LIFE_BALANCE
			"Si Paling Hemat" -> UserType.PALING_HEMAT
			"Si Tukang Shopping" -> UserType.TUKANG_SHOPPING
			else -> UserType.LIFE_BALANCE
		}
		
		binding.btnSave.setOnClickListener {
			val income = binding.inputIncome.text
			
			lifecycleScope.launch {
				presenter.saveInitialIncome(userType, income.toString().toLong())
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
	
	override fun onSaveInitialError(error: String) {
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
	}
	
	override fun onSaveInitialSuccess(amount: Long) {
		intentTo(MainActivity::class.java)
	}
}