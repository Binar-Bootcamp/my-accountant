package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl
import java.util.*

class TransactionPresenter(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl(),
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl()
) : TransactionContract.Presenter, BasePresenter<TransactionContract.View>() {
    override suspend fun saveTransaction(transaction: Transaction) {
        try {
            transactionRepository.createTransaction(transaction)

            val calendar = Calendar.getInstance()
            val id = "${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)}"
            val summary = summaryRepository.findSummaryById(id)
            when (transaction.type) {
                TransactionType.INCOME -> {
                    summary.income += transaction.amount
                    when (summary.type) {
                        UserType.LIFE_BALANCE -> {
                            summary.budget += transaction.amount * 1 / 2
                        }
                        UserType.PALING_HEMAT -> {
                            summary.budget += transaction.amount * 4 / 10
                        }
                        UserType.TUKANG_SHOPPING -> {
                            summary.budget += transaction.amount * 7 / 10
                        }
                    }
                    summary.total = summary.budget - summary.expense
                }
                TransactionType.OUTCOME -> {
                    summary.expense += transaction.amount
                    summary.total = summary.budget - summary.expense
                }
            }

            summaryRepository.createSummary(summary)
            getView()?.onSaveTransactionSuccess()
        } catch (e: Exception) {
            getView()?.onSaveTransactionFailure(e.message.toString())
        }
    }
}