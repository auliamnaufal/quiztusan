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

    fun clearName(){
        repository.add(Constant.PREF_PLAYER_NAME, "")
    }

    fun clearScore(){
        repository.add(Constant.PREF_PLAYER_SCORE, "")
    }

    fun setPosition(position: String){
        repository.add(Constant.PREF_CURRENT_POSITION, position)
    }

    fun getPlayerName(): String? {
        return repository.getString(Constant.PREF_PLAYER_NAME)
    }

    fun addScoreOnPref() {
        val previousScore = repository.getString(Constant.PREF_PLAYER_SCORE)?.toInt()
        val result = previousScore?.plus(1)
        addScore()
        repository.add(Constant.PREF_PLAYER_SCORE, result.toString())
    }

    fun getScore(): String?{
        return repository.getString(Constant.PREF_PLAYER_SCORE)
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

    fun addUsername(name: String){
        quizRef.addValueEventListener(
            object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val player = Player(name, "0")
                    quizRef.child(name).setValue(player)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            }
        )
    }

    fun addScore(){
        quizRef.child(getPlayerName().toString()).addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    quizRef.child(getPlayerName().toString()).child("score").setValue(getScore())
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }
}