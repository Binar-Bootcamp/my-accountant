package com.binaracademy.myaccountant.data.presenter

interface ProfileContract {

    interface Presenter {
        suspend fun getTotalSavingUser()
    }

    interface View {
        fun onSuccessFetchSaving(amount: Long)
    }
}