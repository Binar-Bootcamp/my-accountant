package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.enums.UserType

interface InitialContract {

    interface Presenter {
        suspend fun saveInitialIncome(type: UserType, amount: Long)
    }

    interface View {
        fun onSaveInitialError(error: String)

        fun onSaveInitialSuccess(amount: Long)
    }

}