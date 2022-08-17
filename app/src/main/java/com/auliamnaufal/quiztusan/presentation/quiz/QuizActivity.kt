package com.auliamnaufal.quiztusan.presentation.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.auliamnaufal.quiztusan.R
import com.auliamnaufal.quiztusan.data.local.QuizDummy
import com.auliamnaufal.quiztusan.databinding.ActivityMainBinding
import com.auliamnaufal.quiztusan.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private var _binding: ActivityQuizBinding? = null
    private val binding get() = _binding!!

    private var _viewmodel: QuizViewModel? = null
    private val viewModel get() = _viewmodel as QuizViewModel

    private var listQuiz = QuizDummy.listQuiz

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        _viewmodel = ViewModelProvider(this)[QuizViewModel::class.java]

        viewModel.clearScore()

        setupView()
    }

    private fun setupView() {
        binding.apply {
            val currentPosition = viewModel.getCurrentPosition()
            val quiz = listQuiz[currentPosition!!.toInt()]

            tvQuestion.text = quiz.qustion
            btn1.text = quiz.answer1
            btn2.text = quiz.answer2

            btn1.setOnClickListener {
                if(btn1.text == quiz.correctAnswer){
                    viewModel.addScore()
                }
                val nextPosition = currentPosition.toInt() + 1
                viewModel.setPosition(nextPosition.toString())
                recreate()
            }

            btn2.setOnClickListener {
                if(btn2.text == quiz.correctAnswer){
                    viewModel.addScore()
                }
                val nextPosition = currentPosition.toInt() + 1
                viewModel.setPosition(nextPosition.toString())
                recreate()
            }


        }
    }
}