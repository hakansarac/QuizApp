package com.hakansarac.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        val questionsList = Constants.getQuestions()
        //Log.i("Question Size","${questionsList.size}")
        var coun = 0
        val question : Question? = questionsList[coun]

        progressBarAnswer.progress = coun+1
        textViewProgress.text = "${coun+1}/${progressBarAnswer.max}"
        textViewQuestion.text = question!!.question
        imageViewFlag.setImageResource(question.image)
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
    }
}