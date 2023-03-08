package com.binaracademy.myaccountant.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.binaracademy.myaccountant.data.room.Summary

@Dao
interface SummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSummary(summary: Summary)

    @Query("SELECT * FROM summary WHERE id = :id LIMIT 1")
    fun findById(id: String): Summary

}