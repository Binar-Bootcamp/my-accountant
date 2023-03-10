package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl
import com.binaracademy.myaccountant.util.helpers.DateUtils

class DetailHistoryPresenter(
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl()
): DetailHistoryContract.Presenter, BasePresenter<DetailHistoryContract.View>() {

    override fun getHistoryTransaction(month: Int, year: Int): LiveData<List<Transaction>> {
        val (start, end) = DateUtils.getStartAndEndOfMonth(year, month)
        return transactionRepository.getAllTransactionFromTo(start, end)
    }
}