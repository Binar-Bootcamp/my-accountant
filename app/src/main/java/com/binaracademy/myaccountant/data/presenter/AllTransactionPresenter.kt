package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl

class AllTransactionPresenter(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl(),
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl()
) : AllTransactionContract.Presenter, BasePresenter<AllTransactionContract.View>() {

    private var type: UserType = UserType.LIFE_BALANCE
    private var income: Long = 0L
    private var expense: Long = 0L
    private var budget: Long = 0L
    private var total: Long = 0L

    private lateinit var summary: Summary

    // function to update summary when change in income or expense
    private fun updateSummary(inc: Long = 0L, exp: Long = 0L) {
        // update local variable to tracking
        income += inc
        expense += exp

        // update summary to render in view
        summary = Summary()
        summary.type = type
        summary.income = income
        when (type) {
            UserType.LIFE_BALANCE -> {
                summary.budget = budget - exp
                summary.budget += inc * 1 / 2
            }
            UserType.PALING_HEMAT -> {
                summary.budget = budget - exp
                summary.budget += inc * 6 / 10
            }
            UserType.TUKANG_SHOPPING -> {
                summary.budget = budget - exp
                summary.budget += inc * 3 / 10
            }
        }

        summary.expense = expense
        summary.total = summary.budget - summary.expense
        getView()?.onUpdatedSummarySuccess(summary)
    }

    // function to update summary when change in type
    private fun updateSummary(type: UserType) {
        val prevIncome: Long = when (summary.type) {
            UserType.LIFE_BALANCE -> summary.budget * 2
            UserType.PALING_HEMAT -> summary.budget * 10 / 6
            UserType.TUKANG_SHOPPING -> summary.budget * 10 / 3
        }
        summary = Summary()
        when (type) {
            UserType.LIFE_BALANCE -> summary.budget = prevIncome * 1/2
            UserType.PALING_HEMAT -> summary.budget = prevIncome * 10 / 6
            UserType.TUKANG_SHOPPING -> summary.budget = prevIncome * 10 / 3
        }
        summary.type = type
        summary.expense = expense
        summary.total = summary.budget - summary.expense
        getView()?.onUpdatedSummarySuccess(summary)
    }

    override suspend fun initialFetchDataSummary(id: String) {
        val summary = summaryRepository.findSummaryById(id)
        type = summary.type
        income = summary.income
        expense = summary.expense
        budget = summary.budget
        total = summary.total
        getView()?.onUpdatedSummarySuccess(summary)
    }

    override fun getAllTransactions(): LiveData<List<Transaction>> {
        return transactionRepository.getAllTransaction()
    }
}