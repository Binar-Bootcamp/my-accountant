package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Transaction

interface HistoryContract {

    interface Presenter {
        fun getHistoryTransaction(): LiveData<List<Transaction>>
    }

    interface View
}