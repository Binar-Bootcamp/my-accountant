package com.binaracademy.myaccountant.util.helpers

import java.util.*

object DateUtils {
    fun getStartAndEndOfMonth(year: Int, month: Int): Pair<Long, Long> {
        val startDate: Calendar = Calendar.getInstance()
        val endDate: Calendar = Calendar.getInstance()

        // Set the start date to the first day of the month
        startDate.set(year, month, 1, 0, 0, 0)
        startDate.set(Calendar.MILLISECOND, 0)

        // Set the end date to the last day of the month
        endDate.set(year, month, startDate.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59)
        endDate.set(Calendar.MILLISECOND, 999)
        return Pair(startDate.timeInMillis, endDate.timeInMillis)
    }

    fun getStartAndEndOfMonthTimestampCurrentTime(): Pair<Long, Long> {
        val calendar: Calendar = Calendar.getInstance()
        val startOfMonth = calendar.apply { set(Calendar.DAY_OF_MONTH, 1) }.timeInMillis
        val endOfMonth = calendar.apply {
            set(
                Calendar.DAY_OF_MONTH,
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            )
        }.timeInMillis

        return Pair(startOfMonth, endOfMonth)
    }

}