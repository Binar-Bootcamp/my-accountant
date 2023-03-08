package com.binaracademy.myaccountant

import android.app.Application
import androidx.room.Room
import com.binaracademy.myaccountant.data.room.AppDatabase

class MyAccountantApp: Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }
}