package com.binaracademy.myaccountant.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binaracademy.myaccountant.data.enums.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey var id: Long = 0L,
    var type: TransactionType = TransactionType.INCOME,
    var source: String = "",
    var description: String = "",
    var amount: Long = 0L,
    @ColumnInfo(name = "created_at") var createdAt: Date = Date()
)
