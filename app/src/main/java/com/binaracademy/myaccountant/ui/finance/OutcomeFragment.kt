package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.presenter.TransactionContract
import com.binaracademy.myaccountant.data.presenter.TransactionPresenter
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.FragmentOutcomeBinding
import com.binaracademy.myaccountant.util.helpers.formatString
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.util.*

class OutcomeFragment : Fragment(), TransactionContract.View  {
	private lateinit var binding: FragmentOutcomeBinding

	private val calendar: Calendar = Calendar.getInstance()

	private val presenter = TransactionPresenter()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentOutcomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter.setView(this)
		setupDatePicker()
		setupDropdownType()
		setupHandleUserSubmit()
	}

	override fun onSaveTransactionSuccess() {
		Toast.makeText(requireContext(), "Success Create Expense", Toast.LENGTH_SHORT).show()
		activity?.finish()
	}

	override fun onSaveTransactionFailure(message: String) {
		Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
	}

	private fun setupHandleUserSubmit() {
		binding.btnVpNext.setOnClickListener {
			try {
				val transaction = Transaction()
				transaction.source = binding.autoCmpTypeList.text.toString()
				transaction.type = TransactionType.OUTCOME
				transaction.description = binding.titInputSource.text.toString()
				transaction.amount = binding.titInputIncome.text.toString().toLong()
				transaction.createdAt = Date(calendar.timeInMillis)
				lifecycleScope.launch {
					presenter.saveTransaction(transaction)
				}
			} catch (e: Exception) {
				Toast.makeText(
					requireContext(),
					"Invalid cannot parse input",
					Toast.LENGTH_SHORT
				).show()
			}
		}
	}
	private fun setupDatePicker() {
		val today = MaterialDatePicker.todayInUtcMilliseconds()
		calendar.timeInMillis = today

		val datePicker = MaterialDatePicker.Builder.datePicker()
			.setTitleText("Select date")
			.setSelection(today)
			.build()

		binding.fiDate.setEndIconOnClickListener {
			datePicker.show(parentFragmentManager, "DatePicker")
			datePicker.addOnPositiveButtonClickListener { selected ->
				calendar.timeInMillis = selected
				val dateFormat = calendar.time.formatString("dd MMMM yyyy")
				binding.fiDate.editText?.setText(dateFormat)
			}
		}
	}

	private fun setupDropdownType() {
		val items = listOf(
			"Tagihan",
			"Makan",
			"Hiburan",
			"Rumah",
			"Kendaraan",
			"Belanja",
			"Sosial",
			"Lainnya"
		)
		val adapter = ArrayAdapter(requireActivity(), R.layout.dropdown_items, items)
		binding.autoCmpTypeList.setAdapter(adapter)
	}
}