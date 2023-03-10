package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl
import com.binaracademy.myaccountant.util.helpers.DateUtils

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

    // function to update summary when change in type
    private suspend fun updateSummary(type: UserType) {
        summary = Summary()
        when (type) {
            UserType.LIFE_BALANCE -> summary.budget = income * 1 / 2
            UserType.PALING_HEMAT -> summary.budget = income * 4 / 10
            UserType.TUKANG_SHOPPING -> summary.budget = income * 7 / 10
        }
        budget = summary.budget
        summary.type = type
        summary.income = income
        summary.expense = expense
        summary.total = summary.budget - summary.expense
        summaryRepository.createSummary(summary)
        getView()?.onUpdatedSummarySuccess(summary)
    }

    override suspend fun initialFetchDataSummary(id: String) {
        summary = summaryRepository.findSummaryById(id)
        type = summary.type
        income = summary.income
        expense = summary.expense
        budget = summary.budget
        total = summary.total
        getView()?.onUpdatedSummarySuccess(summary)
    }

    override fun getAllTransactions(): LiveData<List<Transaction>> {
        val (start, end) = DateUtils.getStartAndEndOfMonthTimestampCurrentTime()
        return transactionRepository.getAllTransactionFromTo(start, end)
    }

    override suspend fun changeUserSavingType(userType: UserType) {
        updateSummary(userType)
    }
}