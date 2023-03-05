package com.binaracademy.myaccountant.data

data class Transaction(
	var category: String = "",
	var description: String = "",
	var date: String = "",
	var amount: Long = 0L,
	var photo: Int = 0
)