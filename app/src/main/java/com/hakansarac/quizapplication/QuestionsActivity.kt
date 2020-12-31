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

//TODO: check to cannot change the selection after submit.
class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    //private members' name begins m
    private var mCurrentPos : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPos : Int = 0
    private var mPoints : Int = 0

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
        buttonSubmit.setOnClickListener(this)
    }

    private fun setQuestion(){
        val question = mQuestionsList!![mCurrentPos-1]
        allOptionsView()
        if(mCurrentPos == mQuestionsList!!.size){
            buttonSubmit.text = "SEE RESULTS"
        }else{
            buttonSubmit.text = "SUBMIT"
        }
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
            R.id.buttonSubmit -> {
                if(mSelectedOptionPos == 0){
                    mCurrentPos++
                    when{
                        mCurrentPos <= mQuestionsList!!.size -> {
                            setQuestion()
                        }else->{
                            //TODO: fill this place for end of quiz
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPos-1)
                    if(question!!.correctAnswer != mSelectedOptionPos){
                        answerView(mSelectedOptionPos,R.drawable.wrong_option_border_bg)
                        answerView(question!!.correctAnswer,R.drawable.correct_option_border_bg)
                    }else{
                        mPoints++
                        answerView(question!!.correctAnswer,R.drawable.true_answer_border_bg)
                    }
                    if(mCurrentPos == mQuestionsList!!.size){
                        buttonSubmit.text = "SEE RESULTS"
                    }else{
                        buttonSubmit.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPos = 0
                }
            }
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

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> textViewOptionOne.background = ContextCompat.getDrawable(this,drawableView)
            2 -> textViewOptionTwo.background = ContextCompat.getDrawable(this,drawableView)
            3 -> textViewOptionThree.background = ContextCompat.getDrawable(this,drawableView)
            4 -> textViewOptionFour.background = ContextCompat.getDrawable(this,drawableView)
        }
    }
}