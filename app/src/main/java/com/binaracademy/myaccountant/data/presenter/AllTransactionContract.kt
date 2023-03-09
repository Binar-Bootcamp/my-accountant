package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.enums.UserType
import com.binaracademy.myaccountant.data.room.Summary
import com.binaracademy.myaccountant.data.room.Transaction

interface AllTransactionContract {

    interface Presenter {

        suspend fun initialFetchDataSummary(id: String)

        fun getAllTransactions(): LiveData<List<Transaction>>

        suspend fun changeUserSavingType(userType: UserType)

    }

    interface View {
        fun onUpdatedSummarySuccess(summary: Summary)

    }
}