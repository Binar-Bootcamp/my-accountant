package com.binaracademy.myaccountant.ui.counter.model

import java.io.Serializable



class ModelItem(
	val url : String ,
	val nama : String ,
	val location : String,
	 val price : Int
) : Serializable {
}