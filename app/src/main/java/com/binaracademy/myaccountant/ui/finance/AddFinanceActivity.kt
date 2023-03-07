package com.binaracademy.myaccountant.ui.finance

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.binaracademy.myaccountant.databinding.ActivityAddFinanceBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFinanceActivity : AppCompatActivity() {
	private val binding: ActivityAddFinanceBinding by lazy {
		ActivityAddFinanceBinding.inflate(layoutInflater)
	}
	
	private lateinit var tabLayoutMediator: TabLayoutMediator
	
	private val tabList = mutableListOf("Income", "Outcome")
	private val fragmentList = mutableListOf<Fragment>()
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		
		initData()
		initView()
	}
	
	private fun initData() {
		fragmentList.add(IncomeFragment.newInstance("A", "V"))
		fragmentList.add(OutcomeFragment.newInstance("A", "V"))
	}
	
	private fun initView() {
		val viewPager = binding.viewPager
		val tabs = binding.tabs
		viewPager.offscreenPageLimit = 2
		viewPager.adapter = TabLayoutAdapter(this, fragmentList)
		viewPager.isUserInputEnabled = false
		
		tabLayoutMediator = TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
			tab.text = tabList[position]
		}
		
		tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab) {
				Log.i("TAB", "Select")
				if (tab.position == 0)
					tabs.setSelectedTabIndicatorColor(Color.parseColor("#005ECD"))
				else
					tabs.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"))
			}
			
			override fun onTabUnselected(tab: TabLayout.Tab) {
			}
			
			override fun onTabReselected(tab: TabLayout.Tab) {
			}
		})
		
		tabLayoutMediator.attach()
		
		tabs.tabRippleColor = null
	}
	
	class TabLayoutAdapter(
		fragmentActivity: FragmentActivity,
		private val fragmentList: MutableList<Fragment>
	) :
		FragmentStateAdapter(fragmentActivity) {
		override fun getItemCount(): Int {
			return fragmentList.size
		}
		
		override fun createFragment(position: Int): Fragment {
			return fragmentList[position]
		}
		
	}
}