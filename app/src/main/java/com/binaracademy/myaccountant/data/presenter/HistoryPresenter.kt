package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl

class HistoryPresenter(
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl()
): HistoryContract.Presenter, BasePresenter<HistoryContract.View>() {
    override fun getHistoryTransaction(): LiveData<List<Transaction>> {
        return transactionRepository.getAllTransactionHistory()
    }
}