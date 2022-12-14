package com.auliamnaufal.quiztusan.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.auliamnaufal.quiztusan.utils.Constant

class SharedPreferences(context: Context) {

    var sharedPreferences = context.getSharedPreferences(Constant.PREF_QUIZ, 0)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun add(key: String, value: String){
        editor.putString(key, value)
        editor.apply()
    }

    fun add(key: String, value: Int){
        editor.putInt(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "0")
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}