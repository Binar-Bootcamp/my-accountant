package com.binaracademy.myaccountant.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.databinding.ActivitySplashScreenBinding
import com.binaracademy.myaccountant.ui.landing.LandingActivity
import com.binaracademy.myaccountant.ui.register.RegisterActivity


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
	private val binding: ActivitySplashScreenBinding by lazy {
		ActivitySplashScreenBinding.inflate(layoutInflater)
	}
	
	private lateinit var sharePreference: SharedPreferences
	private lateinit var editor: Editor
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		setUpSharePreference()
		
		val isFirst = sharePreference.getBoolean(IS_FIRST, true)
		
		Handler(Looper.getMainLooper()).postDelayed({
			var i = if (isFirst){
				Intent(this, LandingActivity::class.java)
			} else {
				Intent(this, RegisterActivity::class.java)
			}
			startActivity(i)
			finish()
		}, 2000)
	}
	
	private fun setUpSharePreference() {
		sharePreference = this.getSharedPreferences(TABLE_DATA, Context.MODE_PRIVATE)
		editor = sharePreference.edit()
	}
	
	companion object {
		const val IS_FIRST = "IS_FIRST"
		const val TABLE_DATA = "APP_TABLE"
	}
	
	
	
	
	
}