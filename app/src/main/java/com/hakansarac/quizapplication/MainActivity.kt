package com.hakansarac.quizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)

    }

    fun buttonStart(view : View){
        if(editTextName.text.toString().isEmpty())
            Toast.makeText(this,"Please enter a name",Toast.LENGTH_SHORT).show()
        else{
            val intent = Intent(this,QuestionsActivity::class.java)
            //Pass the name through intent using the constant variable which we have created
            intent.putExtra(Constants.USER_NAME,editTextName.text.toString())       //taking username to result activity
            startActivity(intent)
            finish()
        }
    }
}