package com.binaracademy.myaccountant.ui.finance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.adapter.ListTransactionAdapter
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.presenter.AllTransactionContract
import com.binaracademy.myaccountant.data.presenter.AllTransactionPresenter
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.databinding.FragmentFinanceBinding
import com.binaracademy.myaccountant.util.helpers.NumberFormatter
import com.binaracademy.myaccountant.util.helpers.formatString
import com.binaracademy.myaccountant.util.helpers.intentTo
import kotlinx.coroutines.launch
import java.util.*

class FinanceFragment : Fragment(), AllTransactionContract.View {
    private val presenter = AllTransactionPresenter()
    private lateinit var binding: FragmentFinanceBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenterAndFetchInitial()
        setUpRecycleView()
        setupMenuPopUp()
        binding.tvDateTitle.text = Date().formatString("MMMM yyyy")
        binding.fabAdd.setOnClickListener {
            binding.root.context.intentTo(AddFinanceActivity::class.java)
        }
    }

    override fun onUpdatedSummarySuccess(summary: Summary) {
        binding.tvIncomeAmount.text = NumberFormatter.formatRupiah(summary.income)
        binding.tvExpenseAmount.text = NumberFormatter.formatRupiah(summary.expense)
        binding.tvBudgetAmount.text = NumberFormatter.formatRupiah(summary.budget)
        binding.tvTotalAmount.text = NumberFormatter.formatRupiah(summary.total)
    }

    private fun setupPresenterAndFetchInitial() {
        presenter.setView(this)
        val calendar = Calendar.getInstance()
        val id = "${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)}"
        lifecycleScope.launch {
            presenter.initialFetchDataSummary(id)
        }
    }

    private fun setupMenuPopUp() {
        binding.imgType.setOnClickListener { it ->
            val popup = PopupMenu(activity, it)
            popup.inflate(R.menu.popup_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.life_balance -> {
                        lifecycleScope.launch { presenter.changeUserSavingType(UserType.LIFE_BALANCE) }
                        true
                    }
                    R.id.hemat -> {
                        lifecycleScope.launch { presenter.changeUserSavingType(UserType.PALING_HEMAT) }
                        true
                    }
                    R.id.shopping -> {
                        lifecycleScope.launch { presenter.changeUserSavingType(UserType.TUKANG_SHOPPING) }
                        true
                    }
                    else -> false
                }

            }
            popup.show()
        }
    }

    private fun setUpRecycleView() {
        val layoutManager = LinearLayoutManager(activity)
        recyclerView = binding.rvTransaction

        val adapter = ListTransactionAdapter(arrayListOf())
        presenter.getAllTransactions().observe(viewLifecycleOwner) {
            val calendar = Calendar.getInstance()
            val id = "${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)}"
            lifecycleScope.launch {
                presenter.initialFetchDataSummary(id)
                adapter.updateTransaction(it)
            }
        }
        adapter.setOnItemClickCallback(object : ListTransactionAdapter.OnItemClickCallback {
            override fun onItemClick(data: Transaction) {

            }
        })
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }
}