package com.binaracademy.myaccountant.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class LandingPagerAdapter(
	fragmentActivity: FragmentActivity,
	private vararg val fragment: Fragment,
) : FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return fragment.size
	}
	
	override fun createFragment(position: Int): Fragment {
		return fragment[position]
	}
}