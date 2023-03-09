package com.binaracademy.myaccountant.data.dao

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Transaction

interface TransactionRepository {
    suspend fun createTransaction(transaction: Transaction)

    fun getAllTransactionFromTo(start: Long, to: Long): LiveData<List<Transaction>>

    fun getAllTransactionHistory(): LiveData<List<Transaction>>

}