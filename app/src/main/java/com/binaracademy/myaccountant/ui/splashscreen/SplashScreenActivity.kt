package com.binaracademy.myaccountant.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.binaracademy.myaccountant.databinding.ActivitySplashScreenBinding
import com.binaracademy.myaccountant.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private val binding : ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        },2000)
    }
}