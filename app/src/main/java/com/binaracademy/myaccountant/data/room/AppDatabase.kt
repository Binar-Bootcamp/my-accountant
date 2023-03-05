package com.binaracademy.myaccountant.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binaracademy.myaccountant.data.dao.TransactionDao
import com.binaracademy.myaccountant.data.room.converters.DateConverters

@Database(entities = [(Transaction::class)], version = 1)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}