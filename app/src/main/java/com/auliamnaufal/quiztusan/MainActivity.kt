package com.auliamnaufal.quiztusan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.auliamnaufal.quiztusan.databinding.ActivityMainBinding
import com.auliamnaufal.quiztusan.presentation.quiz.QuizActivity
import com.auliamnaufal.quiztusan.viewmodel.QuizViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var _viewmodel: QuizViewModel? = null
    private val viewModel get() = _viewmodel as QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        _viewmodel = ViewModelProvider(this)[QuizViewModel::class.java]

        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            btnSubmit.setOnClickListener {
                val name = edtUsername.text.toString()
                viewModel.addUsername(name)
                viewModel.setPlayerName(name)
                startActivity(Intent(this@MainActivity, QuizActivity::class.java))
                finish()
            }
        }
    }
}