package com.binaracademy.myaccountant.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binaracademy.myaccountant.databinding.FragmentProfileBinding
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager

class ProfileFragment : Fragment() {
	
	private var _binding: FragmentProfileBinding? = null
	private val binding get() = _binding!!
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = FragmentProfileBinding.inflate(inflater, container, false)
		
		val userTable = Global.USER_TABLE
		val username = Global.USERNAME
		
		val sharedPreferences = SharedPreferencesManager(this.requireActivity(), userTable)
		val usernameValue = sharedPreferences.getString(username, "Your Name")
		
		binding.tvNameProfile.text = usernameValue
		
		return binding.root
	}
}