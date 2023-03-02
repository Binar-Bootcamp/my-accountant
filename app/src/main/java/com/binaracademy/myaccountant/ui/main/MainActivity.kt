package com.binaracademy.myaccountant.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val navHostFragment = binding.containerFragment.getFragment<NavHostFragment>()
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController)
        ////        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        //
        //        supportActionBar?.setHomeButtonEnabled(false)
        //        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val appBarConfiguration = AppBarConfiguration(binding.bottomNav.menu)
        setupActionBarWithNavController(navController, appBarConfiguration)

    }
}