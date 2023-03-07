package com.binaracademy.myaccountant.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.myaccountant.data.adapter.LandingPagerAdapter
import com.binaracademy.myaccountant.databinding.ActivityLandingBinding
import com.binaracademy.myaccountant.ui.main.MainActivity
import com.binaracademy.myaccountant.util.helpers.intentTo

class LandingActivity : AppCompatActivity() {
    private val binding: ActivityLandingBinding by lazy {
        ActivityLandingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                intentTo(MainActivity::class.java)
                finish()
            } else {
                binding.vpLanding.setCurrentItem(currentPosition +1, true)
            }
        }
    }
}