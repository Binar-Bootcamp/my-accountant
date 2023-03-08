package com.binaracademy.myaccountant.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.binaracademy.myaccountant.databinding.ActivitySplashScreenBinding
import com.binaracademy.myaccountant.ui.landing.LandingActivity
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.ui.register.RegisterActivity
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
	private val binding: ActivitySplashScreenBinding by lazy {
		ActivitySplashScreenBinding.inflate(layoutInflater)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		val appTable = Global.APP_TABLE
		val isFirst = Global.IS_FIRST
		
		val sharedPreferences = SharedPreferencesManager(this, appTable)

		val isFirstValue = sharedPreferences.getBoolean(isFirst, true)
		val isUsernameProvided = sharedPreferences.getString("username", "").isNullOrBlank()

		Handler(Looper.getMainLooper()).postDelayed({
			val i = if (isFirstValue){
				Intent(this, LandingActivity::class.java)
			} else if (isUsernameProvided) {
				Intent(this, MainActivity::class.java)
			} else {
				Intent(this, RegisterActivity::class.java)
			}
			startActivity(i)
			finish()
		}, 2000)
	}
}