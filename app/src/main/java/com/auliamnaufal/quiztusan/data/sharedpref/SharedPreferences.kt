package com.auliamnaufal.quiztusan.data.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.auliamnaufal.quiztusan.utils.Constant

class SharedPreferences(context: Context) {

    var sharedPreferences = context.getSharedPreferences(Constant.QUIZ_PREF, 0)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun add(key: String, value: String){
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, "1")
    }
}