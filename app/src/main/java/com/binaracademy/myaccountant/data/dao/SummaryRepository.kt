package com.binaracademy.myaccountant.data.dao

import androidx.lifecycle.LiveData
import com.binaracademy.myaccountant.data.room.Summary

interface SummaryRepository {

    suspend fun createSummary(summary: Summary)

    suspend fun findSummaryById(id: String): Summary

    fun findAllSummary(): List<Summary>

    fun findAllLiveDataSummary(): LiveData<List<Summary>>

}