package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Transaction

interface AllTransactionContract {
    interface Presenter {
        fun getAllTransactions(): LiveData<List<Transaction>>
    }

    interface View
}