package com.example.mathsgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.random.Random

class GameSub : AppCompatActivity() {

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer : EditText

    lateinit var buttonOk : Button
    lateinit var buttonNext : Button

    var correctanswer = 0
    var userscore = 0
    var userlife = 3

    lateinit var timer : CountDownTimer
    private val startTimerInmillis : Long = 60000
    var timeLeftInmillis : Long = startTimerInmillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title = "Subtraction"

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {
            val input = editTextAnswer.text.toString()
            if (input == "")
            {
                Toast.makeText(applicationContext,"Please write an answer or click the next button",
                    Toast.LENGTH_LONG).show()
            }
            else
            {
                pauseTimer()
                val userAnswer = input.toInt()
                if(userAnswer == correctanswer)
                {
                    userscore += 10
                    textQuestion.text = "Congratulations,your answer is correct"
                    textScore.text = userscore.toString()
                }
                else
                {
                    userlife--
                    textQuestion.text = "Sorry,your answer is wrong"
                    textLife.text = userlife.toString()
                }
            }
        }
        buttonNext.setOnClickListener{
            pauseTimer()
            resetTImer()
            gameContinue()
            editTextAnswer.setText("")

            if(userlife == 0)
            {
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameSub,ResultActivity::class.java)
                intent.putExtra("score",userscore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameContinue()
            }

        }
    }

    fun gameContinue()
    {
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        textQuestion.text = "$number1 - $number2"
        correctanswer = number1 - number2

        startTimer()
    }

    fun startTimer()
    {
        timer = object : CountDownTimer(timeLeftInmillis,1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInmillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTImer()
                updateText()

                userlife--
                textLife.text = userlife.toString()
                textQuestion.text = "Sorry,your time is up!"
            }

        }.start()

    }

    fun updateText()
    {
        val remainingTime : Int = (timeLeftInmillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%d",remainingTime)
    }

    fun pauseTimer()
    {
        timer.cancel()
    }

    fun resetTImer()
    {
        timeLeftInmillis = startTimerInmillis
        updateText()
    }
}