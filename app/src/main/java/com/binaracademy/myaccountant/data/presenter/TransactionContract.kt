package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.room.Transaction

interface TransactionContract {
    interface Presenter {
        suspend fun saveTransaction(transaction: Transaction)
    }
    interface View {

        fun onSaveTransactionSuccess()

        fun onSaveTransactionFailure(message: String)

    }
}