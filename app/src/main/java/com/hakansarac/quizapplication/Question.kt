package com.hakansarac.quizapplication

data class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val options: ArrayList<String> = ArrayList(),
    val correctAnswer: Int
)
