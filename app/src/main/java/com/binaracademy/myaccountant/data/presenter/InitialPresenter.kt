package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.dao.SummaryRepository
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import com.binaracademy.myaccountant.data.enums.TransactionType
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.SummaryRepositoryImpl
import com.binaracademy.myaccountant.data.room.Transaction
import com.binaracademy.myaccountant.data.room.TransactionRepositoryImpl

class InitialPresenter(
    private val summaryRepository: SummaryRepository = SummaryRepositoryImpl(),
    private val transactionRepository: TransactionRepository = TransactionRepositoryImpl()
) : BasePresenter<InitialContract.View>(), InitialContract.Presenter {

    override suspend fun saveInitialIncome(type: UserType, amount: Long) {
            if (amount < 100_000) {
                getView()?.onSaveInitialError("Minimum nominal is 100.000.00")
                return
            }

            val transaction = Transaction()
            transaction.amount = amount
            transaction.source = "Income"
            transaction.type = TransactionType.INCOME
            transaction.description = "Initial Income"
            transactionRepository.createTransaction(transaction)

            val summary = Summary()
            summary.type = type
            when (type) {
                UserType.LIFE_BALANCE -> summary.budget = amount * 1/2
                UserType.PALING_HEMAT -> summary.budget = amount * 4/10
                UserType.TUKANG_SHOPPING -> summary.budget = amount * 7/10
            }

            summary.income = amount
            summary.expense = 0L
            summary.total = summary.budget - 0L
            summaryRepository.createSummary(summary)

            getView()?.onSaveInitialSuccess(amount)
        }

}