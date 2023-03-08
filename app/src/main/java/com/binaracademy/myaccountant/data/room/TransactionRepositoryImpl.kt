package com.binaracademy.myaccountant.data.room

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.MyAccountantApp
import com.binaracademy.myaccountant.data.dao.TransactionDao
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class TransactionRepositoryImpl: TransactionRepository {

    private val calendar = Calendar.getInstance()
    private val startOfMonth = calendar.apply { set(Calendar.DAY_OF_MONTH, 1) }.timeInMillis
    private val endOfMonth = calendar.apply { set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) }.timeInMillis
    private val transactionDao: TransactionDao = MyAccountantApp.database.transactionDao()
    private val transactions: LiveData<List<Transaction>> = transactionDao.findAllTransaction(startOfMonth, endOfMonth)

    override suspend fun createTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insertTransaction(transaction)
        }
    }

    override fun getAllTransaction(): LiveData<List<Transaction>> = transactions
}