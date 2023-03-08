package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.binaracademy.myaccountant.databinding.FragmentIncomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class IncomeFragment : Fragment() {
	private lateinit var binding: FragmentIncomeBinding
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		binding = FragmentIncomeBinding.inflate(inflater, container, false)
		
		return binding.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		val today = MaterialDatePicker.todayInUtcMilliseconds()
		val calendar = Calendar.getInstance()
		
		calendar.timeInMillis = today
		
		val datePicker =
			MaterialDatePicker.Builder.datePicker()
				.setTitleText("Select date")
				.setSelection(today)
				.build()
		
		binding.fiDate.setEndIconOnClickListener {
			datePicker.show(parentFragmentManager, "DatePicker");
			datePicker.addOnPositiveButtonClickListener { selected ->
				calendar.timeInMillis = selected
				var dateFormat = SimpleDateFormat("dd MMMM yyyy").format(calendar.time)
				binding.fiDate.editText?.setText(dateFormat)
			}
		}
	}
}