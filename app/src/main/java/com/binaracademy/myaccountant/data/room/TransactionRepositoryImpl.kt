package com.binaracademy.myaccountant.data.room

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.MyAccountantApp
import com.binaracademy.myaccountant.data.dao.TransactionDao
import com.binaracademy.myaccountant.data.dao.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl: TransactionRepository {

    private val transactionDao: TransactionDao = MyAccountantApp.database.transactionDao()

    private val transactions: LiveData<List<Transaction>> = transactionDao.findAllTransaction()

    override suspend fun createTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            transactionDao.insertTransaction(transaction)
        }
    }

    override fun getAllTransaction(): LiveData<List<Transaction>> = transactions
}