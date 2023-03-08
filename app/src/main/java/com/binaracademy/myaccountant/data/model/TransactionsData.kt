package com.binaracademy.myaccountant.data.model

import com.binaracademy.myaccountant.R

object TransactionsData {
	private val transactionCategories = arrayOf(
		"Home",
		"Entertainment",
		"Bill",
		"Shopping",
		"Vehicle",
		"Top Up",
		"Social",
		"Other"
	)
	
	private val transactionDescriptions = arrayOf(
		"Pribadi",
		"Pribadi",
		"Pasta Gigi",
		"Pasta Gigi",
		"Angkot",
		"Uang Saku",
		"Uang Saku",
		"Uang Saku"
	)
	
	private val transactionDates = arrayOf(
		"Desember 2023",
		"Desember 2023",
		"Desember 2023",
		"Desember 2023",
		"Desember 2023",
		"Desember 2023",
		"Desember 2023",
		"Desember 2023"
	)
	
	private val transactionAmounts = arrayOf(
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
		1_200_000L,
	)
	
	private val transactionImages = intArrayOf(
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income,
		R.drawable.ic_category_home_income
	)
	
	val listData: ArrayList<Transaction>
		get() {
			val list = arrayListOf<Transaction>()
			for (position in transactionCategories.indices) {
				val transaction = Transaction()
				transaction.category = transactionCategories[position]
				transaction.description = transactionDescriptions[position]
				transaction.date = transactionDates[position]
				transaction.amount = transactionAmounts[position]
				transaction.photo = transactionImages[position]
				list.add(transaction)
			}
			return list
		}
}