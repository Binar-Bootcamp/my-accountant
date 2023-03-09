package com.binaracademy.myaccountant.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.myaccountant.data.adapter.LandingPagerAdapter
import com.binaracademy.myaccountant.databinding.ActivityLandingBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.ui.register.RegisterActivity
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager
import com.binaracademy.myaccountant.util.helpers.intentTo

class LandingActivity : AppCompatActivity() {
	private val binding: ActivityLandingBinding by lazy {
		ActivityLandingBinding.inflate(layoutInflater)
	}
	
	private lateinit var landingPageAdapter: LandingPagerAdapter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		setUpView()
		
		val appName = Global.APP_TABLE
		val isFirst = Global.IS_FIRST
		
		val sharedPreferences = SharedPreferencesManager(this, appName)
		
		binding.tvSkip.setOnClickListener {
			intentTo(MainActivity::class.java)
			finish()
		}
		
		binding.btnVpNext.setOnClickListener {
			val currentPosition = binding.vpLanding.currentItem
			if (currentPosition == landingPageAdapter.itemCount - 1) {
				sharedPreferences.putBoolean(isFirst, false)
				
				intentTo(RegisterActivity::class.java)
				finish()
			} else {
				binding.vpLanding.setCurrentItem(currentPosition + 1, true)
			}
		}
	}
	
	private fun setUpView() {
		val landingFragmentOne = LandingPageOneFragment()
		val landingFragmentTwo = LandingPageTwoFragment()
		
		landingPageAdapter = LandingPagerAdapter(
			this,
			landingFragmentOne, landingFragmentTwo
		)
		binding.vpLanding.adapter = landingPageAdapter
		binding.dotIndicator.attachTo(binding.vpLanding)
	}
}