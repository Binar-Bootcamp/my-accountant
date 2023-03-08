package com.binaracademy.myaccountant.ui.landing

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.myaccountant.data.adapter.LandingPagerAdapter
import com.binaracademy.myaccountant.databinding.ActivityLandingBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.ui.register.RegisterActivity
import com.binaracademy.myaccountant.ui.splashscreen.SplashScreenActivity
import com.binaracademy.myaccountant.util.helpers.intentTo

class LandingActivity : AppCompatActivity() {
	private val binding: ActivityLandingBinding by lazy {
		ActivityLandingBinding.inflate(layoutInflater)
	}
	
	private lateinit var sharePreference: SharedPreferences
	private lateinit var editor: SharedPreferences.Editor
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		setUpSharePreference()
		
		val landingFragmentOne = LandingPageOneFragment()
		val landingFragmentTwo = LandingPageTwoFragment()
		
		val landingPageAdapter = LandingPagerAdapter(
			this,
			landingFragmentOne, landingFragmentTwo
		)
		binding.vpLanding.adapter = landingPageAdapter
		binding.dotIndicator.attachTo(binding.vpLanding)
		
		binding.tvSkip.setOnClickListener {
			intentTo(MainActivity::class.java)
			finish()
		}
		
		binding.btnVpNext.setOnClickListener {
			val currentPosition = binding.vpLanding.currentItem
			if (currentPosition == landingPageAdapter.itemCount - 1) {
				setUpAction()
				intentTo(RegisterActivity::class.java)
				finish()
			} else {
				binding.vpLanding.setCurrentItem(currentPosition + 1, true)
			}
		}
	}
	
	private fun setUpSharePreference() {
		sharePreference =
			this.getSharedPreferences(SplashScreenActivity.TABLE_DATA, Context.MODE_PRIVATE)
		editor = sharePreference.edit()
	}
	
	private fun setUpAction() {
		editor.putBoolean(SplashScreenActivity.IS_FIRST, false)
		editor.apply()
	}
}