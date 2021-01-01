package com.hakansarac.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTION,0)
        val points = intent.getIntExtra(Constants.CORRECT_ANSWER,0)

        textViewUserName.text = userName?.toUpperCase(Locale.ENGLISH)
        textViewScore.text = "Your score is $points out of $totalQuestions."

    }

    fun buttonFinish(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}