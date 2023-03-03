package com.binaracademy.myaccountant.data

import java.util.UUID

data class CounterData(
    val id: String = UUID.randomUUID().toString(),
    val url: String,
    val nama: String,
    val location: String
)
