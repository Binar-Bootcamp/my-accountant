package com.binaracademy.myaccountant.ui.finance

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.presenter.TransactionContract
import com.binaracademy.myaccountant.data.presenter.TransactionPresenter
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.FragmentIncomeBinding
import com.binaracademy.myaccountant.util.helpers.formatString
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.util.*


class IncomeFragment : Fragment(), TransactionContract.View {

    private lateinit var binding: FragmentIncomeBinding

    private val calendar: Calendar = Calendar.getInstance()

    private val presenter = TransactionPresenter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.setView(this)
        setupDatePicker()
        setupHandleUserSubmit()
    }

    override fun onSaveTransactionSuccess() {
        Toast.makeText(requireContext(), "Success Create Income", Toast.LENGTH_SHORT).show()
        activity?.finish()
    }

    override fun onSaveTransactionFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setupHandleUserSubmit() {
        binding.btnVpNext.setOnClickListener {
            try {
                val transaction = Transaction()
                transaction.source = "Income"
                transaction.type = TransactionType.INCOME
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
}