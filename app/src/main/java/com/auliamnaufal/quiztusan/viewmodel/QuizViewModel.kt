package com.auliamnaufal.quiztusan.viewmodel

import android.app.Application
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
        repository.add(Constant.PREF_CURRENT_POSITION, 0)
    }

    fun clearName(){
        repository.add(Constant.PREF_PLAYER_NAME, "")
    }

    fun clearScore(){
        repository.add(Constant.PREF_PLAYER_SCORE, "")
    }

    fun setPosition(position: Int){
        repository.add(Constant.PREF_CURRENT_POSITION, position)
    }

    fun getPlayerName(): String? {
        return repository.getString(Constant.PREF_PLAYER_NAME)
    }

    fun setScoreOnPref(score: Int) {
        repository.add(Constant.PREF_PLAYER_SCORE, score)
    }

    fun getScore(): Int {
        return repository.getInt(Constant.PREF_PLAYER_SCORE)
    }

    fun setPlayerName(name: String){
        repository.add(Constant.PREF_PLAYER_NAME, name)
    }

    fun getCurrentPosition(): Int {
        return repository.getInt(Constant.PREF_CURRENT_POSITION)
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
        val player = Player(name, "0")
        quizRef.child(name).setValue(player)
        setScoreOnPref(0)
    }

    fun addScore(){
        val result = getScore()?.toInt()?.plus(1)
        quizRef.child(getPlayerName().toString()).child("score").setValue(result)
        setScoreOnPref(result)
    }
}