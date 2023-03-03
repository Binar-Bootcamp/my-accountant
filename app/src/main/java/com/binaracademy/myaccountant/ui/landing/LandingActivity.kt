package com.binaracademy.myaccountant.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binaracademy.myaccountant.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private val binding : ActivityLandingBinding by lazy{
        ActivityLandingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}