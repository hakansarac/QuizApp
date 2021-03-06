package com.hakansarac.quizapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*


class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    //private members' name begins m
    private var mCurrentPos : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPos : Int = 0
    private var mPoints : Int = 0
    private var isSelected : Boolean = false
    private var isSubmitted : Boolean = false
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        //STEP 4: Get the NAME from intent and assign it the variable.
        mUserName = intent.getStringExtra(Constants.USER_NAME)
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

    /**
     * setting UI of new questions
     */
    private fun setQuestion(){
        val question = mQuestionsList!![mCurrentPos-1]
        allOptionsView()

        buttonSubmit.text = "SUBMIT"

        progressBarAnswer.progress = mCurrentPos
        textViewProgress.text = "$mCurrentPos/${progressBarAnswer.max}"
        textViewQuestion.text = question!!.question
        imageViewFlag.setImageResource(question.image)
        textViewOptionOne.text = question.optionOne
        textViewOptionTwo.text = question.optionTwo
        textViewOptionThree.text = question.optionThree
        textViewOptionFour.text = question.optionFour
    }

    /**
     * set the options by default when it just created
     */
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

            R.id.textViewOptionOne -> {
                when{ !isSubmitted -> {
                    isSelected = true
                    selectedOptionView(textViewOptionOne,1)
                }}
            }
            R.id.textViewOptionTwo -> {
                when{ !isSubmitted -> {
                    isSelected = true
                    selectedOptionView(textViewOptionTwo,2)
                }}
            }
            R.id.textViewOptionThree -> {
                when{ !isSubmitted -> {
                    isSelected = true
                    selectedOptionView(textViewOptionThree,3)
                }}
            }
            R.id.textViewOptionFour -> {
                when{ !isSubmitted -> {
                    isSelected = true
                    selectedOptionView(textViewOptionFour,4)
                }}
            }


            R.id.buttonSubmit -> {
                if(isSubmitted){            //if user submitted the answer already, we go to next question when user presses button.
                    isSubmitted = false     //the submit button will be activate.
                    mCurrentPos++           //question position will be increased by one.
                    when{
                        mCurrentPos <= mQuestionsList!!.size -> {   //if questions are still not over
                            isSelected = false                      //all answers will be unselected
                            setQuestion()                           //new question will be created
                        }else->{                                    //if questions are over, go to result activity
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWER,mPoints)
                            intent.putExtra(Constants.TOTAL_QUESTION,mQuestionsList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else if(isSelected && !isSubmitted){       //if user selected an answer but not submitted yet; when user presses button, we check the correctness of answer
                    isSubmitted = true
                    val question = mQuestionsList?.get(mCurrentPos-1)
                    if(question!!.correctAnswer != mSelectedOptionPos){     //if user's answer is wrong then do...
                        answerView(mSelectedOptionPos,R.drawable.wrong_option_border_bg)    //submitted but wrong option
                        answerView(question!!.correctAnswer,R.drawable.correct_option_border_bg) //not submitted but correct option
                    }else{      //if users answer is correct then do...
                        mPoints++
                        answerView(question!!.correctAnswer,R.drawable.true_answer_border_bg)   //submitted and correct option
                    }
                    if(mCurrentPos == mQuestionsList!!.size){       //set the button text as "see result" if user submit the answer and there is no question anymore.
                        buttonSubmit.text = "SEE RESULTS"
                    }else{                                          //set the button text as "next question" if user submit the answer and there is still any questions.
                        buttonSubmit.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPos = 0
                }
                else{
                    isSubmitted = false
                    isSelected = false
                    Toast.makeText(this,"Please select an option.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * set the selected option view
     */
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

    /**
     * set the options according to correct or incorrect
     */
    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> textViewOptionOne.background = ContextCompat.getDrawable(this,drawableView)
            2 -> textViewOptionTwo.background = ContextCompat.getDrawable(this,drawableView)
            3 -> textViewOptionThree.background = ContextCompat.getDrawable(this,drawableView)
            4 -> textViewOptionFour.background = ContextCompat.getDrawable(this,drawableView)
        }
    }
}