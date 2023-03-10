package com.binaracademy.myaccountant.data.presenter

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Summary

interface HistoryContract {

    interface Presenter {
        fun getHistoryTransaction(): LiveData<List<Summary>>
    }

    interface View
}