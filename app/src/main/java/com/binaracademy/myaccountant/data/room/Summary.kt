package com.binaracademy.myaccountant.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.binaracademy.myaccountant.data.enums.UserType
import kotlinx.parcelize.Parcelize
import java.util.Calendar

@Parcelize
@Entity(tableName = "summary")
data class Summary(
    @PrimaryKey var id: String = "${Calendar.getInstance().get(Calendar.MONTH)}-${Calendar.getInstance().get(Calendar.YEAR)}",
    var type: UserType = UserType.LIFE_BALANCE,
    var income: Long = 0L,
    var expense: Long = 0L,
    var budget: Long = 0L,
    var total: Long = 0L
): Parcelable
