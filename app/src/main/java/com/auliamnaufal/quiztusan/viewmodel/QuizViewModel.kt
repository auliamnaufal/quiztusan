package com.auliamnaufal.quiztusan.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.auliamnaufal.quiztusan.model.Player
import com.auliamnaufal.quiztusan.data.QuizRepository
import com.auliamnaufal.quiztusan.utils.Constant
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class QuizViewModel(application: Application): AndroidViewModel(application) {
    private var repository = QuizRepository()

    private val quizRef = repository.getReference(Constant.REF_QUIZ_SCORE)

    fun getScoreboard(): MutableLiveData<List<Player>> {
        val listPlayer = MutableLiveData<List<Player>>()
        quizRef.addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val mListScore = arrayListOf<Player>()
                    for(i in snapshot.children){
                        val player = i.getValue(Player::class.java)
                        player?.let { mListScore.add(it) }
                    }
                    listPlayer.value = mListScore
                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
        )
        return listPlayer
    }

    fun addUsername(user: Player){
        quizRef.addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = user.name
                    if (name != null) {
                        quizRef.child(name).setValue(user)
                    } else {
                        Toast.makeText(getApplication(), "nama telah di gunakan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
        )
    }

    fun addScore(user: Player){
        user.name?.let {
            quizRef.child(it).addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val previousScore = snapshot.getValue(Player::class.java)?.score
                        if (previousScore != null) {
                            quizRef.child(user.name!!).setValue(previousScore + 1)
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show()
                    }

                }
            )
        }
    }
}