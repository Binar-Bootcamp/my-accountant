package com.binaracademy.myaccountant.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.presenter.ProfileContract
import com.binaracademy.myaccountant.data.presenter.ProfilePresenter
import com.binaracademy.myaccountant.databinding.FragmentProfileBinding
import com.binaracademy.myaccountant.util.helpers.Global
import com.binaracademy.myaccountant.util.helpers.SharedPreferencesManager
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(), ProfileContract.View {

	private var _binding: FragmentProfileBinding? = null
	private val binding get() = _binding!!
	private val presenter = ProfilePresenter()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentProfileBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.setView(this)
		lifecycleScope.launch {
			presenter.getTotalSavingUser()
			setupUsernameProfile()
		}
	}

	private fun setupUsernameProfile() {
		val userTable = Global.USER_TABLE
		val username = Global.USERNAME

		val sharedPreferences = SharedPreferencesManager(this.requireActivity(), userTable)
		val usernameValue = sharedPreferences.getString(username, "Your Name")
		binding.tvNameProfile.text = usernameValue
	}

	override fun onSuccessFetchSaving(amount: Long) {
		binding.tvSavingProfile.text = resources.getString(R.string.currency_amount, amount)
	}
}