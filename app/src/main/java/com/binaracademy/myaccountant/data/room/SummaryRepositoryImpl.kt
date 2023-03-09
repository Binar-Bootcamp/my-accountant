package com.binaracademy.myaccountant.data.room

import com.binaracademy.myaccountant.MyAccountantApp
import com.binaracademy.myaccountant.data.dao.SummaryDao
import com.binaracademy.myaccountant.data.dao.SummaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SummaryRepositoryImpl : SummaryRepository {
    private val summaryDao: SummaryDao = MyAccountantApp.database.summaryDao()

    override suspend fun createSummary(summary: Summary) {
        withContext(Dispatchers.IO) {
            summaryDao.insertSummary(summary)
        }
    }

    override suspend fun findSummaryById(id: String): Summary {
        return withContext(Dispatchers.IO) {
            summaryDao.findById(id)
        }
    }

}