package com.binaracademy.myaccountant.data.dao

import com.binaracademy.myaccountant.data.room.Summary

interface SummaryRepository {

    suspend fun createSummary(summary: Summary)

    suspend fun findSummaryById(id: String): Summary

    suspend fun findAllSummary(): List<Summary>

}