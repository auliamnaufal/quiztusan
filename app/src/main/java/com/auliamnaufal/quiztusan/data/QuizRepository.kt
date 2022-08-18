package com.auliamnaufal.quiztusan.data

import android.content.Context
import com.auliamnaufal.quiztusan.data.sharedpref.SharedPreferences
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QuizRepository(context: Context) {
    private var quizRef: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var pref = SharedPreferences(context)

    fun getReference(ref: String): DatabaseReference {
        return quizRef.getReference(ref)
    }

    fun add(key: String, value: String){
        pref.add(key, value)
    }

    fun add(key: String, value: Int){
        pref.add(key, value)
    }

    fun getString(key: String): String? {
        return pref.getString(key)
    }

    fun getInt(key: String): Int {
        return pref.getInt(key)
    }
}