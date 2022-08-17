package com.auliamnaufal.quiztusan.data.local

import com.auliamnaufal.quiztusan.model.Quiz

object QuizDummy {
    var listQuiz = listOf<Quiz>(
        Quiz(
            "Kapankan Indonesia Merdeka?",
            "1945",
            "1495",
            1
        ),
        Quiz(
            "Siapakah Presiden Pertama Republik Indonesia?",
            "Naufal",
            "Soekarno",
            2
        )
    )
}