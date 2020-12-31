package com.hakansarac.quizapplication

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    //private members' name begins m
    private var mCurrentPos : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPos : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mQuestionsList = Constants.getQuestions()
        //Log.i("Question Size","${questionsList.size}")
        setQuestion()

        //this class(activity) must be OnClickListener.
        //we implement View.OnClickListener interface
        //then implement onClick override method
        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)
    }

    private fun setQuestion(){
        mCurrentPos = 1
        val question = mQuestionsList!![mCurrentPos-1]
        allOptionsView()
        progressBarAnswer.progress = mCurrentPos
        textViewProgress.text = "$mCurrentPos/${progressBarAnswer.max}"
        textViewQuestion.text = question!!.question
        imageViewFlag.setImageResource(question.image)
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
    }

    private fun allOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,textViewOptionOne)
        options.add(1,textViewOptionTwo)
        options.add(2,textViewOptionThree)
        options.add(3,textViewOptionFour)

        for (option in options){
            option.setTextColor(Color.parseColor("#606060"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.textViewOptionOne -> selectedOptionView(textViewOptionOne,1)
            R.id.textViewOptionTwo -> selectedOptionView(textViewOptionTwo,2)
            R.id.textViewOptionThree -> selectedOptionView(textViewOptionThree,3)
            R.id.textViewOptionFour -> selectedOptionView(textViewOptionFour,4)
        }
    }

    private fun selectedOptionView(textView:TextView,selectedOption:Int){
        allOptionsView()    //when selected new option, all options must be default first.
        mSelectedOptionPos = selectedOption

        textView.setTextColor(Color.parseColor("#303030"))
        textView.setTypeface(textView.typeface,Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}