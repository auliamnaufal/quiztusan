package com.auliamnaufal.quiztusan.presentation.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auliamnaufal.quiztusan.data.local.QuizDummy
import com.auliamnaufal.quiztusan.databinding.ActivityMainBinding
import com.auliamnaufal.quiztusan.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private var _binding: ActivityQuizBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {

    }
}