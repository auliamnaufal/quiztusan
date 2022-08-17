package com.auliamnaufal.quiztusan.presentation.quiz

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
    private var repository = QuizRepository(application)

    private val quizRef = repository.getReference(Constant.REF_QUIZ_SCORE)

    fun clearPosition(){
        repository.add(Constant.PREF_CURRENT_POSITION, "0")
    }

    fun setPosition(position: String){
        repository.add(Constant.PREF_CURRENT_POSITION, position)
    }

    fun getPlayerName(): String? {
        return repository.getString(Constant.PREF_PLAYER_NAME)
    }

    fun setPlayerName(name: String){
        repository.add(Constant.PREF_PLAYER_NAME, name)
    }

    fun getCurrentPosition(): String? {
        return repository.getString(Constant.PREF_CURRENT_POSITION)
    }

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

    fun addScore(name: String){
        quizRef.child(name).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val previousScore = snapshot.getValue(Player::class.java)?.score
                    if (previousScore != null) {
                        quizRef.child(name).setValue(previousScore + 1)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }
}