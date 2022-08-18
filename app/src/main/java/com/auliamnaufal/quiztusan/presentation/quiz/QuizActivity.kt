package com.auliamnaufal.quiztusan.presentation.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.auliamnaufal.quiztusan.data.local.QuizDummy
import com.auliamnaufal.quiztusan.databinding.ActivityQuizBinding
import com.auliamnaufal.quiztusan.presentation.result.ResultActivity
import com.auliamnaufal.quiztusan.viewmodel.QuizViewModel

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

        setupView()
    }

    private fun setupView() {
        binding.apply {
            val currentPosition = viewModel.getCurrentPosition()
            if(currentPosition >= listQuiz.size) { intentToResult() }
            val quiz = listQuiz[currentPosition]

            tvQuestion.text = quiz.qustion
            btn1.text = quiz.answer1
            btn2.text = quiz.answer2

            btn1.setOnClickListener {
                if (btn1.text == quiz.correctAnswer) {
                    viewModel.addScore()
                }
                val nextPosition = currentPosition + 1
                viewModel.setPosition(nextPosition)
                recreate()
            }

            btn2.setOnClickListener {
                if (btn2.text == quiz.correctAnswer) {
                    viewModel.addScore()
                }
                val nextPosition = currentPosition + 1
                viewModel.setPosition(nextPosition)
                recreate()
            }

        }
    }

    private fun intentToResult() {
        startActivity(
            Intent(applicationContext, ResultActivity::class.java)
        )
        finish()
        viewModel.clearPosition()
        viewModel.clearScore()
        viewModel.clearName()
    }
}