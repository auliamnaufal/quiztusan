package com.auliamnaufal.quiztusan.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QuizRepository {
    var quizRef: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getReference(ref: String): DatabaseReference {
        return quizRef.getReference(ref)
    }
}