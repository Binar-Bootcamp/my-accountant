package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.databinding.FragmentIncomeBinding
import com.binaracademy.myaccountant.databinding.FragmentOutcomeBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class OutcomeFragment : Fragment() {
	private lateinit var binding: FragmentOutcomeBinding
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentOutcomeBinding.inflate(inflater, container, false)
		
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
		binding.typeList.setAdapter(adapter)
		
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