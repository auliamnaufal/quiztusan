package com.auliamnaufal.quiztusan.presentation.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.auliamnaufal.quiztusan.databinding.ActivityQuizBinding
import com.auliamnaufal.quiztusan.databinding.ActivityResultBinding
import com.auliamnaufal.quiztusan.viewmodel.QuizViewModel

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!

    private var _viewmodel: QuizViewModel? = null
    private val viewModel get() = _viewmodel as QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _viewmodel = ViewModelProvider(this)[QuizViewModel::class.java]

        setUpView()
    }

    private fun setUpView() {

    }
}