package com.binaracademy.myaccountant.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binaracademy.myaccountant.R
import com.binaracademy.myaccountant.data.enums.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var type: TransactionType = TransactionType.INCOME,
    var source: String = "",
    var description: String = "",
    var amount: Long = 0L,
    @ColumnInfo(name = "created_at") var createdAt: Date = Date()
) {
    companion object {
        val INCOME = intArrayOf(
            R.drawable.ic_category_home_income,
            R.drawable.ic_category_bill_income,
            R.drawable.ic_category_shopping_income,
            R.drawable.ic_category_entertaintment_income,
            R.drawable.ic_category_social_income,
            R.drawable.ic_category_vehicle_income
        )

        val EXPENSE =  intArrayOf(
            R.drawable.ic_category_home_expense,
            R.drawable.ic_category_bill_expense,
            R.drawable.ic_category_shopping_expense,
            R.drawable.ic_category_entertaintment_expense,
            R.drawable.ic_category_social_expense,
            R.drawable.ic_category_vehicle_expense
        )
    }
}
