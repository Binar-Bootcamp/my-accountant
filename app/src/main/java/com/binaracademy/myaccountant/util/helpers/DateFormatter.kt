package com.binaracademy.myaccountant.util.helpers

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatString(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("id", "ID"))
    return dateFormat.format(this)
}