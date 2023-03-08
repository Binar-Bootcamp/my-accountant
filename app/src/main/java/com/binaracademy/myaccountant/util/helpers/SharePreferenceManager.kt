package com.binaracademy.myaccountant.util.helpers

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context, private val tableName: String) {
	
	private fun getSharedPreferences(): SharedPreferences {
		return context.getSharedPreferences(tableName, Context.MODE_PRIVATE)
	}
	
	fun getString(key: String, defaultValue: String): String? {
		return getSharedPreferences().getString(key, defaultValue)
	}
	
	fun getInt(key: String, defaultValue: Int): Int? {
		return getSharedPreferences().getInt(key, defaultValue)
	}
	
	fun getBoolean(key: String, defaultValue: Boolean): Boolean {
		return getSharedPreferences().getBoolean(key, defaultValue)
	}
	
	fun putString(key: String, value: String) {
		val editor = getSharedPreferences().edit()
		editor.putString(key, value)
		editor.apply()
	}
	
	fun putInt(key: String, value: Int) {
		val editor = getSharedPreferences().edit()
		editor.putInt(key, value)
		editor.apply()
	}
	
	fun putBoolean(key: String, value: Boolean) {
		val editor = getSharedPreferences().edit()
		editor.putBoolean(key, value)
		editor.apply()
	}
	
	fun remove(key: String) {
		val editor = getSharedPreferences().edit()
		editor.remove(key)
		editor.apply()
	}
}