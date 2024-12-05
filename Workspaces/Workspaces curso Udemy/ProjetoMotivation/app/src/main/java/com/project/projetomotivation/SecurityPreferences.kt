package com.project.projetomotivation

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

}