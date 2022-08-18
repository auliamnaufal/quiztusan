package com.auliamnaufal.quiztusan.presentation.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auliamnaufal.quiztusan.databinding.ActivityQuizBinding
import com.auliamnaufal.quiztusan.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}