package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.Transaction

interface AllTransactionContract {

    interface Presenter {

        suspend fun initialFetchDataSummary(id: String)

        fun getAllTransactions(): LiveData<List<Transaction>>
    }

    interface View {
        fun onUpdatedSummarySuccess(summary: Summary)

    }
}