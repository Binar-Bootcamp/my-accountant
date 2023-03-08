package com.binaracademy.myaccountant.util.helpers

import java.text.NumberFormat
import java.util.*

object NumberFormatter {
	fun formatRupiah(amount: Long): String {
		val localeID = Locale("in", "ID")
		val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
		return formatRupiah.format(amount).replace(",00", "")
	}
}