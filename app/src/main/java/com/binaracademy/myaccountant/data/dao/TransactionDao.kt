package com.binaracademy.myaccountant.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binaracademy.myaccountant.data.room.Transaction
import java.util.*

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE created_at BETWEEN :start AND :end ORDER BY created_at DESC")
    fun findAllTransactionFromTo(start: Long, end: Long): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions ORDER BY created_at DESC")
    fun findAllTransaction(): LiveData<List<Transaction>>

}