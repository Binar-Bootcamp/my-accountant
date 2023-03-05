package com.binaracademy.myaccountant.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.myaccountant.data.adapter.LandingPagerAdapter
import com.binaracademy.myaccountant.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private val binding : ActivityLandingBinding by lazy{
        ActivityLandingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val landingFragmentOne = LandingPageOneFragment()
        val landingFragmentTwo = LandingPageTwoFragment()

        val landingPageAdapter = LandingPagerAdapter(
            fragmentManager = supportFragmentManager,
            landingFragmentOne, landingFragmentTwo
        )
        binding.vpLanding.adapter = landingPageAdapter
        binding.dotIndicator.attachTo(binding.vpLanding)
    }
}