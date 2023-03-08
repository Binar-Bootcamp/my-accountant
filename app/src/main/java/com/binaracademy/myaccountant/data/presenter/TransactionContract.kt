package com.binaracademy.myaccountant.data.presenter

import com.binaracademy.myaccountant.data.enums.UserType

interface TransactionContract {
    interface Presenter {


        fun changeUserSavingType(userType: UserType)

    }
    interface View {


        fun onChangedUserSavingType()

    }
}